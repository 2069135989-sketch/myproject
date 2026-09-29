package com.innodealing.onshore.practice.service.impl;

import com.innodealing.commons.object.BeanCopyUtils;
import com.innodealing.onshore.bondmetadata.exception.BusinessException;
import com.innodealing.onshore.practice.dao.BondPrimaryStatV2DAO;
import com.innodealing.onshore.practice.model.dto.request.BondStatsV2RequestDTO;
import com.innodealing.onshore.practice.model.dto.response.BaseBondStatsV2AmountDTO;
import com.innodealing.onshore.practice.model.entity.group.BondPrimaryStatV2GroupDO;
import com.innodealing.onshore.practice.model.enums.PrimaryBondFilterDateEnum;
import com.innodealing.onshore.practice.model.bo.BondStatsV2FilterBO;
import com.innodealing.onshore.practice.service.BondPrimaryStatV2Service;
import com.innodealing.onshore.practice.util.BigDecimalUtils;
import com.innodealing.onshore.practice.util.NetFinancingAmountUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 一级发行统计 —— 服务实现（逻辑层）
 */
@Service
public class BondPrimaryStatV2ServiceImpl implements BondPrimaryStatV2Service {

    @Resource
    private BondPrimaryStatV2DAO bondPrimaryStatV2DAO;

    @Override
    public BaseBondStatsV2AmountDTO listBondAmountStats(BondStatsV2RequestDTO requestDTO) {

        // ============ 步骤1：RequestDTO → 查询条件（口径日期 + 筛选 + 时间范围）============
        BondStatsV2FilterBO filterBO = buildFilterBO(requestDTO);

        // ============ 步骤2：调用数据层，跑“不带分组的聚合查询” ============
        List<BondPrimaryStatV2GroupDO> groups = bondPrimaryStatV2DAO.listGroupByTotalFromBase(filterBO);

        // ============ 步骤3：聚合结果 → 响应 DTO（含空值兜底）============
        BaseBondStatsV2AmountDTO result = new BaseBondStatsV2AmountDTO();
        if (groups.isEmpty()) {
            // 没有命中任何记录：返回全空对象即可，配合 @JsonSerialize 会自动显示 "--"
            return result;
        }
        BondPrimaryStatV2GroupDO group = groups.get(0);

        BigDecimal issueAmount = group.getIssueAmount();
        BigDecimal maturityAmount = group.getMaturityAmount();
        BigDecimal deleteCancelAmount = group.getDeleteCancelAmount();
        BigDecimal issueCoupon = group.getIssueCoupon();
        BigDecimal issueAmountCoupon = group.getIssueAmountCoupon();

        // 区间发行
        result.setActualIssueAmount(issueAmount);
        // 区间到期
        result.setMaturityAmount(maturityAmount);
        // 净融资额 = 发行额 - 到期额（与 NetFinancingAmountUtils.calculate 口径一致）
        result.setNetFinancingAmount(NetFinancingAmountUtils.calculate(issueAmount, maturityAmount));
        // 占总发行：推迟/取消 ÷ 区间发行 × 100
        if (Objects.nonNull(issueAmount) && Objects.isNull(deleteCancelAmount)) {
            result.setTotalIssueAmountRatio(BigDecimal.ZERO);
        } else {
            BigDecimal issueAmountRatio = BigDecimalUtils
                    .safeDivide(deleteCancelAmount, issueAmount, RoundingMode.HALF_UP).orElse(null);
            result.setTotalIssueAmountRatio(
                    Objects.nonNull(issueAmountRatio) ? issueAmountRatio.multiply(BigDecimal.valueOf(100)) : null);
        }
        // 发行数量
        result.setIssueNum(group.getIssueNum());
        // 推迟/延迟发行
        result.setDelayOrCancelAmount(deleteCancelAmount);
        // 加权平均票面 = 发行票息合计 ÷ 加权票面分母（数据库里已按 issue_coupon IS NOT NULL 过滤）
        BigDecimal avgIssueCoupon = BigDecimalUtils
                .safeDivide(issueCoupon, issueAmountCoupon, RoundingMode.HALF_UP).orElse(null);
        result.setAvgIssueCoupon(avgIssueCoupon);

        return result;
    }

    /**
     * 步骤1 核心：RequestDTO → 查询条件对象。
     * <p>
     * 把前端字段翻译为 SQL 能识别的 WHERE 条件：
     * • dateType（1公告日…9到期日，默认4缴款日）→ 决定用哪一列当“口径日期”
     * • 各类 List&lt;Integer&gt; 筛选 → IN (...) 条件
     * • startDate/endDate → 用口径日期列做范围过滤
     */
    private BondStatsV2FilterBO buildFilterBO(BondStatsV2RequestDTO requestDTO) {
        // 复制筛选类
        BondStatsV2FilterBO bondStatsV2FilterBO = BeanCopyUtils.copyProperties(requestDTO, BondStatsV2FilterBO.class);
        // 不为空默认缴款日
        if (Objects.isNull(bondStatsV2FilterBO.getDateType())) {
            bondStatsV2FilterBO.setDateType(PrimaryBondFilterDateEnum.PAY_DATE.getValue());
        }
        // 处理开始/结束日期（校验 + 口径列解析已在 DAO 内完成）
        resolve(bondStatsV2FilterBO);
        return bondStatsV2FilterBO;
    }

    public void resolve(BondStatsV2FilterBO bondStatsV2FilterBO) {
        // 先取可空的 Date，避免不传日期时 null.toLocalDate() 直接 NPE
        Date startDate = bondStatsV2FilterBO.getStartDate();
        Date endDate = bondStatsV2FilterBO.getEndDate();
        // 都不传：不限制口径日期范围，DAO 的 addBusinessDateFilter 会跳过日期过滤，聚合返回全量数据
        if (Objects.isNull(startDate) && Objects.isNull(endDate)) {
            return;
        }
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            throw new BusinessException("startDate和endDate必须同时传入");
        }
        LocalDate startLocal = startDate.toLocalDate();
        LocalDate endLocal = endDate.toLocalDate();
        if (startLocal.isAfter(endLocal)) {
            throw new BusinessException("startDate不能晚于endDate");
        }
    }
}
