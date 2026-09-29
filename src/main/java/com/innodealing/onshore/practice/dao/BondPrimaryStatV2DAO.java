package com.innodealing.onshore.practice.dao;

import com.github.wz2cool.dynamic.BaseFilterDescriptor;
import com.github.wz2cool.dynamic.FilterGroupDescriptor;
import com.github.wz2cool.dynamic.GroupByQuery;
import com.github.wz2cool.dynamic.lambda.GetCommonPropertyFunction;
import com.github.wz2cool.dynamic.lambda.GetDatePropertyFunction;
import com.github.wz2cool.dynamic.lambda.GetLongPropertyFunction;
import com.innodealing.onshore.practice.config.constant.BondPrimaryStatV2Constant;
import com.innodealing.onshore.practice.mapper.BondPrimaryStatV2GroupMapper;
import com.innodealing.onshore.practice.model.bo.BondStatsV2FilterBO;
import com.innodealing.onshore.practice.model.entity.BondPrimaryStatV2DO;
import com.innodealing.onshore.practice.model.entity.group.BondPrimaryStatV2GroupDO;
import com.innodealing.onshore.practice.model.enums.PrimaryBondFilterDateEnum;
import com.innodealing.onshore.practice.model.enums.StatDateTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static com.github.wz2cool.dynamic.builder.DynamicQueryBuilderHelper.between;
import static com.github.wz2cool.dynamic.builder.DynamicQueryBuilderHelper.greaterThan;
import static com.github.wz2cool.dynamic.builder.DynamicQueryBuilderHelper.greaterThanOrEqual;
import static com.github.wz2cool.dynamic.builder.DynamicQueryBuilderHelper.in;
import static com.github.wz2cool.dynamic.builder.DynamicQueryBuilderHelper.isEqual;
import static com.github.wz2cool.dynamic.builder.DynamicQueryBuilderHelper.lessThanOrEqual;

/**
 * 一级发行统计 —— 数据访问层（无 XML 写法，与原项目 PgBondPrimaryStatV2DAO 一致）。
 * <p>
 * 聚合查询完全由 mybatis-dynamic-query 的 {@link GroupByQuery} 在运行时拼装：
 * <ul>
 *   <li>源实体 {@link BondPrimaryStatV2DO} 提供 WHERE / GROUP BY 的列；</li>
 *   <li>聚合结果实体 {@link BondPrimaryStatV2GroupDO} 通过 {@code @Column(name="sum(...) FILTER(...)")} 声明聚合列；</li>
 *   <li>表名由 {@link BondPrimaryStatV2Constant#BASE_BOND_PRIMARY_STAT_TABLE} 动态传入；</li>
 *   <li>最终 SQL 由 {@code GroupedExtQueryProvider} 生成，全程不写 XML。</li>
 * </ul>
 */
@Repository
public class BondPrimaryStatV2DAO {

    @Resource
    private BondPrimaryStatV2GroupMapper bondPrimaryStatV2GroupMapper;

    /** 地区/行业“其他”请求码：地区用 -1，行业用 0。 */
    private static final Long AREA_OTHER_REQUEST_CODE = -1L;
    private static final Long INDUSTRY_OTHER_REQUEST_CODE = 0L;
    /** 数据库里“其他”统一存成 0（地区/行业 0 和 NULL 都算“其他”）。 */
    private static final Long DATABASE_OTHER_CODE = 0L;

    /**
     * 总量（不分组）聚合：对应原项目 listGroupByTotalFromBase。
     *
     * @param filter 筛选条件
     * @return 单行聚合结果（无命中时返回空列表）
     */
    public List<BondPrimaryStatV2GroupDO> listGroupByTotalFromBase(BondStatsV2FilterBO filter) {
        return queryBaseTableGroupBy(filter, new ArrayList<>(), new ArrayList<>(),null);
    }

    /**
     * 基础统计表分组聚合（与原项目 queryBaseTableGroupBy 一致）。
     *
     * @param filter            筛选条件
     * @param groupFunctions    结果实体上需要额外选出的分组列（总量查询传空）
     * @param selectFunctions   源实体上需要 GROUP BY 的列（总量查询传空）
     * @param additionalFilters 额外过滤条件（可为 null）
     * @return 聚合结果列表
     */
    private List<BondPrimaryStatV2GroupDO> queryBaseTableGroupBy(
            BondStatsV2FilterBO filter,
            List<GetCommonPropertyFunction<BondPrimaryStatV2GroupDO>> groupFunctions,
            List<GetCommonPropertyFunction<BondPrimaryStatV2DO>> selectFunctions,
            FilterGroupDescriptor<BondPrimaryStatV2DO> additionalFilters) {
        FilterGroupDescriptor<BondPrimaryStatV2DO> filters = listCommonFilters(filter);
        if (additionalFilters != null) {
            filters.addFilters(additionalFilters);
        }
        BaseFilterDescriptor<BondPrimaryStatV2DO>[] commonFilters = filters.getFilters();

        // 聚合列固定选 6 个：票息合计 / 发行额 / 推迟取消额 / 发行数 / 到期额 / 加权票面分母
        List<GetCommonPropertyFunction<BondPrimaryStatV2GroupDO>> selectAll = new ArrayList<>(groupFunctions);
        selectAll.add(BondPrimaryStatV2GroupDO::getIssueCoupon);
        selectAll.add(BondPrimaryStatV2GroupDO::getIssueAmount);
        selectAll.add(BondPrimaryStatV2GroupDO::getDeleteCancelAmount);
        selectAll.add(BondPrimaryStatV2GroupDO::getIssueNum);
        selectAll.add(BondPrimaryStatV2GroupDO::getMaturityAmount);
        selectAll.add(BondPrimaryStatV2GroupDO::getIssueAmountCoupon);

        GroupByQuery<BondPrimaryStatV2DO, BondPrimaryStatV2GroupDO> select = GroupByQuery
                .createQuery(BondPrimaryStatV2DO.class, BondPrimaryStatV2GroupDO.class)
                .select(selectAll.toArray(new GetCommonPropertyFunction[0]));
        select.and(commonFilters);

        if (CollectionUtils.isNotEmpty(selectFunctions)) {
            return bondPrimaryStatV2GroupMapper.selectByGroupedQueryWithTable(
                    select.groupBy(selectFunctions.toArray(new GetCommonPropertyFunction[0])),
                    BondPrimaryStatV2Constant.BASE_BOND_PRIMARY_STAT_TABLE);
        }
        // 总量：不 GROUP BY 任何维度，得到单行聚合
        return bondPrimaryStatV2GroupMapper.selectByGroupedQueryWithTable(
                select.groupBy(),
                BondPrimaryStatV2Constant.BASE_BOND_PRIMARY_STAT_TABLE);
    }

    /**
     * 拼接通用过滤条件（合并原项目父/子 DAO 的 filter 逻辑）。
     */
    public FilterGroupDescriptor<BondPrimaryStatV2DO> listCommonFilters(BondStatsV2FilterBO bondStatsV2FilterBO) {
        FilterGroupDescriptor<BondPrimaryStatV2DO> filters = new FilterGroupDescriptor<BondPrimaryStatV2DO>()
                .and(BondPrimaryStatV2DO::getDeleted, isEqual(0))
                .and(CollectionUtils.isNotEmpty(bondStatsV2FilterBO.getSecondMarkets()),
                        BondPrimaryStatV2DO::getSecondMarket, in(bondStatsV2FilterBO.getSecondMarkets()))
                .and(Objects.nonNull(bondStatsV2FilterBO.getPublicOffering()),
                        BondPrimaryStatV2DO::getPublicOffering, isEqual(bondStatsV2FilterBO.getPublicOffering()))
                .and(CollectionUtils.isNotEmpty(bondStatsV2FilterBO.getEmbeddedOptions()),
                        BondPrimaryStatV2DO::getEmbeddedOption, in(bondStatsV2FilterBO.getEmbeddedOptions()))
                .and(Objects.nonNull(bondStatsV2FilterBO.getCouponRateFilterType()),
                        BondPrimaryStatV2DO::getCouponRateFilterType, in(bondStatsV2FilterBO.getCouponRateFilterType()))
                .and(Objects.nonNull(bondStatsV2FilterBO.getCrossMarketStatus()),
                        BondPrimaryStatV2DO::getCrossMarketStatus, isEqual(bondStatsV2FilterBO.getCrossMarketStatus()))
                .and(CollectionUtils.isNotEmpty(bondStatsV2FilterBO.getBondExtRatingFilterMappings()),
                        BondPrimaryStatV2DO::getBondExtRatingFilterMapping, in(bondStatsV2FilterBO.getBondExtRatingFilterMappings()))
                .and(Objects.nonNull(bondStatsV2FilterBO.getComListedStatus()),
                        BondPrimaryStatV2DO::getComListedStatus, isEqual(bondStatsV2FilterBO.getComListedStatus()))
                .and(CollectionUtils.isNotEmpty(bondStatsV2FilterBO.getBusinessFilterNatures()),
                        BondPrimaryStatV2DO::getBusinessFilterNature, in(bondStatsV2FilterBO.getBusinessFilterNatures()))
                .and(CollectionUtils.isNotEmpty(bondStatsV2FilterBO.getUdicStatusList()),
                        BondPrimaryStatV2DO::getUdicStatus, in(bondStatsV2FilterBO.getUdicStatusList()))
                .and(CollectionUtils.isNotEmpty(bondStatsV2FilterBO.getComExtRatingFilterMappings()),
                        BondPrimaryStatV2DO::getComExtRatingFilterMapping, in(bondStatsV2FilterBO.getComExtRatingFilterMappings()))
                .and(Objects.nonNull(bondStatsV2FilterBO.getMinTbSpreadBp()),
                        BondPrimaryStatV2DO::getTbSpreadBp, greaterThanOrEqual(bondStatsV2FilterBO.getMinTbSpreadBp()))
                .and(Objects.nonNull(bondStatsV2FilterBO.getMaxTbSpreadBp()),
                        BondPrimaryStatV2DO::getTbSpreadBp, lessThanOrEqual(bondStatsV2FilterBO.getMaxTbSpreadBp()))
                .and(filterGroupDescriptor -> filterGroupDescriptor
                        .or(Objects.nonNull(bondStatsV2FilterBO.getScienceTechNoteStatus()),
                                BondPrimaryStatV2DO::getScienceTechNoteStatus, isEqual(bondStatsV2FilterBO.getScienceTechNoteStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getStiStatus()),
                                BondPrimaryStatV2DO::getStiStatus, isEqual(bondStatsV2FilterBO.getStiStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getTier1Status()),
                                BondPrimaryStatV2DO::getTier1Status, isEqual(bondStatsV2FilterBO.getTier1Status()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getTier2Status()),
                                BondPrimaryStatV2DO::getTier2Status, isEqual(bondStatsV2FilterBO.getTier2Status()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getCorporateFinanceStatus()),
                                BondPrimaryStatV2DO::getCorporateFinanceStatus, isEqual(bondStatsV2FilterBO.getCorporateFinanceStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getNonBankFinanceStatus()),
                                BondPrimaryStatV2DO::getNonBankFinanceStatus, isEqual(bondStatsV2FilterBO.getNonBankFinanceStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getGreenBondStatus()),
                                BondPrimaryStatV2DO::getGreenBondStatus, isEqual(bondStatsV2FilterBO.getGreenBondStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getCarbonNeutralityStatus()),
                                BondPrimaryStatV2DO::getCarbonNeutralityStatus, isEqual(bondStatsV2FilterBO.getCarbonNeutralityStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getRuralRevivalStatus()),
                                BondPrimaryStatV2DO::getRuralRevivalStatus, isEqual(bondStatsV2FilterBO.getRuralRevivalStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getInsurancePerpetualStatus()),
                                BondPrimaryStatV2DO::getInsurancePerpetualStatus, isEqual(bondStatsV2FilterBO.getInsurancePerpetualStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getPandaBondStatus()),
                                BondPrimaryStatV2DO::getPandaBondStatus, isEqual(bondStatsV2FilterBO.getPandaBondStatus()))
                        .or(Objects.nonNull(bondStatsV2FilterBO.getHighGrowthSectorBondStatus()),
                                BondPrimaryStatV2DO::getHighGrowthSectorBondStatus, isEqual(bondStatsV2FilterBO.getHighGrowthSectorBondStatus())));
        // 期限过滤（与信用债一级发行保持一致：自定义期限区间；tenorTags 标签映射在原项目 bondmetadata 中，练习版不引入）
        addCreditPrimaryTenorFilter(filters, bondStatsV2FilterBO);
        // ---- 以下为原项目子 DAO 追加的过滤条件 ----
        addBondTypeFilter(filters, bondStatsV2FilterBO);
        addBusinessDateFilter(filters, bondStatsV2FilterBO);
        addAreaCodeFilter(filters, BondPrimaryStatV2DO::getProvinceUniCode, bondStatsV2FilterBO.getProvinceUniCodes());
        addAreaCodeFilter(filters, BondPrimaryStatV2DO::getCityUniCode, bondStatsV2FilterBO.getCityUniCodes());
        addAreaCodeFilter(filters, BondPrimaryStatV2DO::getDistrictUniCode, bondStatsV2FilterBO.getDistrictUniCodes());
        GetLongPropertyFunction<BondPrimaryStatV2DO> induLevel1Filter = isDmStandard(bondStatsV2FilterBO)
                ? BondPrimaryStatV2DO::getDmInduLevel1Code : BondPrimaryStatV2DO::getInduLevel1Code;
        GetLongPropertyFunction<BondPrimaryStatV2DO> induLevel2Filter = isDmStandard(bondStatsV2FilterBO)
                ? BondPrimaryStatV2DO::getDmInduLevel2Code : BondPrimaryStatV2DO::getInduLevel2Code;
        addIndustryCodeFilter(filters, induLevel1Filter, bondStatsV2FilterBO.getInduLevel1Codes());
        addIndustryCodeFilter(filters, induLevel2Filter, bondStatsV2FilterBO.getInduLevel2Codes());
        if (CollectionUtils.isNotEmpty(bondStatsV2FilterBO.getComUniCodes())) {
            filters.and(BondPrimaryStatV2DO::getComUniCode, in(bondStatsV2FilterBO.getComUniCodes()));
        }
        if (bondStatsV2FilterBO.getStartIssueEndDate() != null) {
            filters.and(BondPrimaryStatV2DO::getIssueEndDate, greaterThanOrEqual(bondStatsV2FilterBO.getStartIssueEndDate()));
        }
        if (bondStatsV2FilterBO.getEndIssueEndDate() != null) {
            filters.and(BondPrimaryStatV2DO::getIssueEndDate, lessThanOrEqual(bondStatsV2FilterBO.getEndIssueEndDate()));
        }
        return filters;
    }

    /** 期限过滤：练习版仅支持自定义期限区间（start/end tenor）。 */
    private void addCreditPrimaryTenorFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                            BondStatsV2FilterBO bondStatsV2FilterBO) {
        List<Integer> tenorTags = bondStatsV2FilterBO.getTenorTags();
        if (CollectionUtils.isEmpty(tenorTags)) {
            addCustomTenorRangeFilter(filters, bondStatsV2FilterBO);
            return;
        }
        // 原项目 tenorTags → 天数区间映射依赖 bondmetadata.TenorFilterV2Enum，练习版不引入该依赖，
        // 这里仍按自定义期限区间兜底过滤。
        addCustomTenorRangeFilter(filters, bondStatsV2FilterBO);
    }

    private void addCustomTenorRangeFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                          BondStatsV2FilterBO bondStatsV2FilterBO) {
        toTenorDays(bondStatsV2FilterBO.getStartTenor(), bondStatsV2FilterBO.getStartTenorUnit())
                .ifPresent(startTenorDays -> filters.and(BondPrimaryStatV2DO::getBondTenorDay, greaterThan(startTenorDays)));
        toTenorDays(bondStatsV2FilterBO.getEndTenor(), bondStatsV2FilterBO.getEndTenorUnit())
                .ifPresent(endTenorDays -> filters.and(BondPrimaryStatV2DO::getBondTenorDay, lessThanOrEqual(endTenorDays)));
    }

    /** 期限（数值 + 单位：1 日 / 2 月 / 3 年）→ 天数；无法解析时返回 empty。 */
    private Optional<Integer> toTenorDays(String tenor, Integer unit) {
        if (tenor == null || unit == null) {
            return Optional.empty();
        }
        try {
            BigDecimal value = new BigDecimal(tenor);
            switch (unit) {
                case 1:
                    return Optional.of(value.intValue());
                case 2:
                    return Optional.of(value.multiply(BigDecimal.valueOf(30)).intValue());
                case 3:
                    return Optional.of(value.multiply(BigDecimal.valueOf(365)).intValue());
                default:
                    return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /** 债券类型过滤（含政策债细分：bondTypePar=10 且发行人命中政策债列表）。 */
    private void addBondTypeFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters, BondStatsV2FilterBO filter) {
        List<Integer> bondTypePars = filter.getBondFilterTypes();
        List<Long> policyBankComUniCodes = filter.getBondTypeComUniCodes();
        if (CollectionUtils.isEmpty(bondTypePars) && CollectionUtils.isEmpty(policyBankComUniCodes)) {
            return;
        }
        if (CollectionUtils.isEmpty(policyBankComUniCodes)) {
            filters.and(BondPrimaryStatV2DO::getBondTypePar, in(bondTypePars));
            return;
        }
        if (CollectionUtils.isEmpty(bondTypePars)) {
            filters.and(group -> group
                    .and(BondPrimaryStatV2DO::getBondTypePar, isEqual(10))
                    .and(BondPrimaryStatV2DO::getComUniCode, in(policyBankComUniCodes)));
            return;
        }
        filters.and(group -> group
                .or(BondPrimaryStatV2DO::getBondTypePar, in(bondTypePars))
                .or(policyBankGroup -> policyBankGroup
                        .and(BondPrimaryStatV2DO::getBondTypePar, isEqual(10))
                        .and(BondPrimaryStatV2DO::getComUniCode, in(policyBankComUniCodes))));
    }

    /**
     * 口径日期过滤：按 dateType 选出的日期列，在 [startDate, endDate] 范围内，
     * 同时限制 stat_date_type（0 起息日口径 / 1 到期日口径）。
     */
    private void addBusinessDateFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters, BondStatsV2FilterBO filter) {
        if (filter.getStartDate() == null && filter.getEndDate() == null) {
            return;
        }
        DateGroupColumn issueDateColumn = getSelectedDateColumn(filter.getDateType());
        DateGroupColumn maturityDateColumn = getActualMaturityDateColumn();
        filters.and(group -> group
                .or(issueGroup -> addDateRange(issueGroup
                                .and(BondPrimaryStatV2DO::getStatDateType,
                                        isEqual(StatDateTypeEnum.INTEREST_DATE.getValue())),
                        issueDateColumn.filter, filter))
                .or(maturityGroup -> addDateRange(maturityGroup
                                .and(BondPrimaryStatV2DO::getStatDateType,
                                        isEqual(StatDateTypeEnum.MATURITY_DATE.getValue())),
                        maturityDateColumn.filter, filter)));
    }

    private FilterGroupDescriptor<BondPrimaryStatV2DO> addDateRange(
            FilterGroupDescriptor<BondPrimaryStatV2DO> group,
            GetDatePropertyFunction<BondPrimaryStatV2DO> dateColumn,
            BondStatsV2FilterBO filter) {
        if (filter.getStartDate() != null) {
            group.and(dateColumn, greaterThanOrEqual(filter.getStartDate()));
        }
        if (filter.getEndDate() != null) {
            group.and(dateColumn, lessThanOrEqual(filter.getEndDate()));
        }
        return group;
    }

    /** 地区仅使用 -1 表示“其他”；数据库中的 0 和 NULL 都属于该分类。 */
    private void addAreaCodeFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                  GetLongPropertyFunction<BondPrimaryStatV2DO> property,
                                  List<Long> requestedCodes) {
        if (containsCode(requestedCodes, DATABASE_OTHER_CODE)) {
            addNoMatchCodeFilter(filters, property);
            return;
        }
        addZeroOrNullCodeFilter(filters, property, requestedCodes, AREA_OTHER_REQUEST_CODE);
    }

    /** 行业仅使用 0 表示“其他”；数据库中的 0 和 NULL 都属于该分类。 */
    private void addIndustryCodeFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                      GetLongPropertyFunction<BondPrimaryStatV2DO> property,
                                      List<Long> requestedCodes) {
        addZeroOrNullCodeFilter(filters, property, requestedCodes, INDUSTRY_OTHER_REQUEST_CODE);
    }

    /** 将请求中的“其他”编码转换为数据库 code = 0 或 code IS NULL 条件。 */
    private void addZeroOrNullCodeFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                        GetLongPropertyFunction<BondPrimaryStatV2DO> property,
                                        List<Long> requestedCodes,
                                        Long requestedOtherCode) {
        List<Long> normalCodes = extractNormalCodes(requestedCodes, requestedOtherCode);
        boolean includesOther = containsCode(requestedCodes, requestedOtherCode);
        if (includesOther) {
            List<Long> codesIncludingZero = new ArrayList<>(normalCodes);
            codesIncludingZero.add(DATABASE_OTHER_CODE);
            addCodeOrNullFilter(filters, property, codesIncludingZero);
            return;
        }
        if (CollectionUtils.isNotEmpty(normalCodes)) {
            addCodeInFilter(filters, property, normalCodes);
        }
    }

    private void addCodeInFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                GetLongPropertyFunction<BondPrimaryStatV2DO> property,
                                List<Long> codes) {
        filters.and(property, in(codes));
    }

    /** code IN (...) OR code IS NULL */
    private void addCodeOrNullFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                    GetLongPropertyFunction<BondPrimaryStatV2DO> property,
                                    List<Long> codes) {
        filters.and(group -> group
                .or(property, in(codes))
                .or(property, isEqual(null)));
    }

    /** 构造恒不成立的条件，地区前端误传 0 时返回空数据。 */
    private void addNoMatchCodeFilter(FilterGroupDescriptor<BondPrimaryStatV2DO> filters,
                                     GetLongPropertyFunction<BondPrimaryStatV2DO> property) {
        filters.and(group -> group
                .and(property, isEqual(DATABASE_OTHER_CODE))
                .and(property, isEqual(null)));
    }

    private List<Long> extractNormalCodes(List<Long> requestedCodes, Long requestedOtherCode) {
        if (CollectionUtils.isEmpty(requestedCodes)) {
            return new ArrayList<>();
        }
        return requestedCodes.stream()
                .filter(Objects::nonNull)
                .filter(code -> !Objects.equals(code, requestedOtherCode))
                .collect(java.util.stream.Collectors.toList());
    }

    private boolean containsCode(List<Long> requestedCodes, Long expectedCode) {
        return CollectionUtils.isNotEmpty(requestedCodes) && requestedCodes.stream()
                .anyMatch(code -> Objects.equals(code, expectedCode));
    }

    /** 行业口径：industryStandard = 2 为 DM，其余（含不传）均为申万。 */
    private boolean isDmStandard(BondStatsV2FilterBO filter) {
        return Objects.nonNull(filter) && Objects.equals(filter.getIndustryStandard(), 2);
    }

    /**
     * 根据 dateType 选出“口径日期列”。
     * 1 公告日 … 9 实际到期日；默认 4 缴款日（与 PrimaryBondFilterDateEnum 一致）。
     */
    private DateGroupColumn getSelectedDateColumn(Integer dateType) {
        int selectedDateType = dateType == null
                ? PrimaryBondFilterDateEnum.PAY_DATE.getValue() : dateType;
        switch (selectedDateType) {
            case 1:
                return new DateGroupColumn(BondPrimaryStatV2DO::getBulletinDate,
                        BondPrimaryStatV2DO::getBulletinDate,
                        BondPrimaryStatV2GroupDO::getBulletinDate, BondPrimaryStatV2GroupDO::getBulletinDate);
            case 2:
                return new DateGroupColumn(BondPrimaryStatV2DO::getIssueStartDate,
                        BondPrimaryStatV2DO::getIssueStartDate,
                        BondPrimaryStatV2GroupDO::getIssueStartDate, BondPrimaryStatV2GroupDO::getIssueStartDate);
            case 3:
                return new DateGroupColumn(BondPrimaryStatV2DO::getIssueEndDate,
                        BondPrimaryStatV2DO::getIssueEndDate,
                        BondPrimaryStatV2GroupDO::getIssueEndDate, BondPrimaryStatV2GroupDO::getIssueEndDate);
            case 5:
                return new DateGroupColumn(BondPrimaryStatV2DO::getListedDate,
                        BondPrimaryStatV2DO::getListedDate,
                        BondPrimaryStatV2GroupDO::getListedDate, BondPrimaryStatV2GroupDO::getListedDate);
            case 6:
                return new DateGroupColumn(BondPrimaryStatV2DO::getInterestStartDate,
                        BondPrimaryStatV2DO::getInterestStartDate,
                        BondPrimaryStatV2GroupDO::getInterestStartDate, BondPrimaryStatV2GroupDO::getInterestStartDate);
            case 7:
                return new DateGroupColumn(BondPrimaryStatV2DO::getBidStartDate,
                        BondPrimaryStatV2DO::getBidStartDate,
                        BondPrimaryStatV2GroupDO::getBidStartDate, BondPrimaryStatV2GroupDO::getBidStartDate);
            case 8:
                return new DateGroupColumn(BondPrimaryStatV2DO::getBidEndDate,
                        BondPrimaryStatV2DO::getBidEndDate,
                        BondPrimaryStatV2GroupDO::getBidEndDate, BondPrimaryStatV2GroupDO::getBidEndDate);
            case 9:
                return new DateGroupColumn(BondPrimaryStatV2DO::getActualMaturityDate,
                        BondPrimaryStatV2DO::getActualMaturityDate,
                        BondPrimaryStatV2GroupDO::getActualMaturityDate, BondPrimaryStatV2GroupDO::getActualMaturityDate);
            case 4:
            default:
                return new DateGroupColumn(BondPrimaryStatV2DO::getPayDate,
                        BondPrimaryStatV2DO::getPayDate,
                        BondPrimaryStatV2GroupDO::getPayDate, BondPrimaryStatV2GroupDO::getPayDate);
        }
    }

    private DateGroupColumn getActualMaturityDateColumn() {
        return new DateGroupColumn(BondPrimaryStatV2DO::getActualMaturityDate,
                BondPrimaryStatV2DO::getActualMaturityDate,
                BondPrimaryStatV2GroupDO::getActualMaturityDate,
                BondPrimaryStatV2GroupDO::getActualMaturityDate);
    }

    /**
     * 日期列描述：source（源实体列，用于 SELECT 分组列）、filter（源实体列，用于 WHERE 范围过滤）、
     * result（结果实体列，用于分组回填）、reader（结果实体读取函数）。
     * 总量查询只用 filter；result/reader 为分组/日期维度查询预留。
     */
    private static final class DateGroupColumn {
        private final GetCommonPropertyFunction<BondPrimaryStatV2DO> source;
        private final GetDatePropertyFunction<BondPrimaryStatV2DO> filter;
        private final GetCommonPropertyFunction<BondPrimaryStatV2GroupDO> result;
        private final Function<BondPrimaryStatV2GroupDO, Date> reader;

        private DateGroupColumn(GetCommonPropertyFunction<BondPrimaryStatV2DO> source,
                               GetDatePropertyFunction<BondPrimaryStatV2DO> filter,
                               GetCommonPropertyFunction<BondPrimaryStatV2GroupDO> result,
                               Function<BondPrimaryStatV2GroupDO, Date> reader) {
            this.source = source;
            this.filter = filter;
            this.result = result;
            this.reader = reader;
        }
    }
}
