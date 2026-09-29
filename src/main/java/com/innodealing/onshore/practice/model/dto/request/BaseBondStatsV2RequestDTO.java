package com.innodealing.onshore.practice.model.dto.request;

import com.innodealing.onshore.practice.model.enums.PrimaryBondFilterDateEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 债券： 一级发行统计v2
 *
 * @Author lxw
 * @Date 2026/9/28
 **/
public class BaseBondStatsV2RequestDTO {
    @ApiModelProperty(value = "开始时间")
    private Date startDate;

    @ApiModelProperty(value = "结束时间")
    private Date endDate;

    @ApiModelProperty("日期类型：1 公告日、2 发行起始日、3 发行截止日、4 缴款日、5 上市日、6 起息日、7 招标日、8 截标日、9 到期日；不传默认缴款日")
    private Integer dateType = PrimaryBondFilterDateEnum.PAY_DATE.getValue();

    @ApiModelProperty("债券类型：1 超短融、2 短融、3 中票、4 企业债、5 公司债、7 PPN、8 可转债、9 可交换债、10 其他债务融资工具、11 政策性银行次级债、12 商业银行普通债、13 商业银行次级债、14 二级资本工具、15 TLAC债、16 证券公司债、17 证券公司次级、18 证券公司短融、19 保险债、20 其他金融债、21 ABS、22 同业存单、99 其他、100 全部类型、101 全部利率、102 国债、103 国开、104 口行、105 农发、106 地方债")
    private List<Integer> bondFilterTypes;

    @ApiModelProperty("债券市场： 1 深圳证券交易所;2 上海证券交易所; 3 银行间市场;99 其他")
    private List<Integer> secondMarkets;

    @ApiModelProperty("募集方式： 0： 私募； 1：公募")
    private Integer publicOffering;

    @ApiModelProperty("发行条款： 0:含权 1：不含权 2:永续")
    private List<Integer> embeddedOptions;

    @ApiModelProperty("1：固息; 2 浮息; 999：其他")
    private Integer couponRateFilterType;

    @ApiModelProperty("跨市场： 0 否； 1：是")
    private Integer crossMarketStatus;

    @ApiModelProperty("主体外部评级筛选: 1 AAA; 2 AA+; 3: AA; 4: AA-; 5: A+; 999:其他")
    private List<Integer> comExtRatingFilterMappings;

    @ApiModelProperty("债券外部评级筛选: 1 AAA; 2 AA+; 3: AA; 4: AA-; 5: A+; 999:其他")
    private List<Integer> bondExtRatingFilterMappings;

    @ApiModelProperty("发行人是否上市： 0: 否； 1： 是")
    private Integer comListedStatus;

    @ApiModelProperty("企业性质（经营类型过滤使用）1:央企, 2:国企, 3:民企, 999:其他")
    private List<Integer> businessFilterNatures;

    @ApiModelProperty("债券期限（与信用债一级发行口径一致，左开右闭）:1.<=3M 2.3-6M 3.6-9M 4.9-12M 5.1-3Y 6.3-5Y 9.>5Y 99.其他")
    private List<Integer> tenorTags;

    @ApiModelProperty("开始期限")
    private String startTenor;

    @ApiModelProperty("开始期限单位：1-日  2-月  3-年")
    private Integer startTenorUnit;

    @ApiModelProperty("结束期限")
    private String endTenor;

    @ApiModelProperty("结束期限单位：1-日  2-月  3-年")
    private Integer endTenorUnit;

    @ApiModelProperty("是否城投列表: 1 金融 2 产业 3 城投 4 类城投 99 其他")
    private List<Integer> udicStatusList;

    @ApiModelProperty("债券发行状态：0:  发行中; 1:  已经上市; 2:  延迟发行; 3:  取消发行")
    private List<Integer> issueStatus;

    @ApiModelProperty("标普PCA评分筛选项映射，1 aaa序列;  2 aa序列;  3 a序列; 4 bbb序列；5 bb序列；6 b序列及以下；999:其他")
    private List<Integer> pcaFilterRatingMappings;

    @ApiModelProperty("债券主题")
    private List<Integer> bondThemeList;

    @ApiModelProperty("关注组ID列表")
    private List<Long> groupIds;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long userId;

    @ApiModelProperty("最小国债利差BP")
    private BigDecimal minTbSpreadBp;

    @ApiModelProperty("最大国债利差BP")
    private BigDecimal maxTbSpreadBp;

    @ApiModelProperty("发行截止日-开始")
    private Date startIssueEndDate;

    @ApiModelProperty("发行截止日-结束")
    private Date endIssueEndDate;

    public Date getStartDate() {
        return Objects.isNull(startDate) ? null : new Date(startDate.getTime());
    }

    public void setStartDate(Date startDate) {
        this.startDate = Objects.isNull(startDate) ? null : new Date(startDate.getTime());
    }

    public Date getEndDate() {
        return Objects.isNull(endDate) ? null : new Date(endDate.getTime());
    }

    public void setEndDate(Date endDate) {
        this.endDate = Objects.isNull(endDate) ? null : new Date(endDate.getTime());
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = Objects.isNull(dateType)
                ? PrimaryBondFilterDateEnum.PAY_DATE.getValue() : dateType;
    }

    public List<Integer> getBondFilterTypes() {
        return Objects.isNull(bondFilterTypes) ? new ArrayList<>() : new ArrayList<>(bondFilterTypes);
    }

    public void setBondFilterTypes(List<Integer> bondFilterTypes) {
        this.bondFilterTypes = Objects.isNull(bondFilterTypes) ? new ArrayList<>() : new ArrayList<>(bondFilterTypes);
    }

    public List<Integer> getSecondMarkets() {
        return Objects.isNull(secondMarkets) ? new ArrayList<>() : new ArrayList<>(secondMarkets);
    }

    public void setSecondMarkets(List<Integer> secondMarkets) {
        this.secondMarkets = Objects.isNull(secondMarkets) ? new ArrayList<>() : new ArrayList<>(secondMarkets);
    }

    public Integer getPublicOffering() {
        return publicOffering;
    }

    public void setPublicOffering(Integer publicOffering) {
        this.publicOffering = publicOffering;
    }

    public List<Integer> getEmbeddedOptions() {
        return Objects.isNull(embeddedOptions) ? new ArrayList<>() : new ArrayList<>(embeddedOptions);
    }

    public void setEmbeddedOptions(List<Integer> embeddedOptions) {
        this.embeddedOptions = Objects.isNull(embeddedOptions) ? new ArrayList<>() : new ArrayList<>(embeddedOptions);
    }

    public Integer getCouponRateFilterType() {
        return couponRateFilterType;
    }

    public void setCouponRateFilterType(Integer couponRateFilterType) {
        this.couponRateFilterType = couponRateFilterType;
    }

    public Integer getCrossMarketStatus() {
        return crossMarketStatus;
    }

    public void setCrossMarketStatus(Integer crossMarketStatus) {
        this.crossMarketStatus = crossMarketStatus;
    }

    public List<Integer> getComExtRatingFilterMappings() {
        return Objects.isNull(comExtRatingFilterMappings)
                ? new ArrayList<>() : new ArrayList<>(comExtRatingFilterMappings);
    }

    public void setComExtRatingFilterMappings(List<Integer> comExtRatingFilterMappings) {
        this.comExtRatingFilterMappings = Objects.isNull(comExtRatingFilterMappings)
                ? new ArrayList<>() : new ArrayList<>(comExtRatingFilterMappings);
    }

    public List<Integer> getBondExtRatingFilterMappings() {
        return Objects.isNull(bondExtRatingFilterMappings) ? new ArrayList<>() : new ArrayList<>(bondExtRatingFilterMappings);
    }

    public void setBondExtRatingFilterMappings(List<Integer> bondExtRatingFilterMappings) {
        this.bondExtRatingFilterMappings = Objects.isNull(bondExtRatingFilterMappings) ?
                new ArrayList<>() : new ArrayList<>(bondExtRatingFilterMappings);
    }

    public Integer getComListedStatus() {
        return comListedStatus;
    }

    public void setComListedStatus(Integer comListedStatus) {
        this.comListedStatus = comListedStatus;
    }

    public List<Integer> getBusinessFilterNatures() {
        return Objects.isNull(businessFilterNatures) ? new ArrayList<>() : new ArrayList<>(businessFilterNatures);
    }

    public void setBusinessFilterNatures(List<Integer> businessFilterNatures) {
        this.businessFilterNatures = Objects.isNull(businessFilterNatures) ? new ArrayList<>() : new ArrayList<>(businessFilterNatures);
    }

    public List<Integer> getTenorTags() {
        return Objects.isNull(tenorTags) ? new ArrayList<>() : new ArrayList<>(tenorTags);
    }

    public void setTenorTags(List<Integer> tenorTags) {
        this.tenorTags = Objects.isNull(tenorTags) ? new ArrayList<>() : new ArrayList<>(tenorTags);
    }

    public List<Integer> getUdicStatusList() {
        return Objects.isNull(udicStatusList) ? new ArrayList<>() : new ArrayList<>(udicStatusList);
    }

    public void setUdicStatusList(List<Integer> udicStatusList) {
        this.udicStatusList = Objects.isNull(udicStatusList) ? new ArrayList<>() : new ArrayList<>(udicStatusList);
    }

    public List<Integer> getIssueStatus() {
        return Objects.isNull(issueStatus) ? new ArrayList<>() : new ArrayList<>(issueStatus);
    }

    public void setIssueStatus(List<Integer> issueStatus) {
        this.issueStatus = Objects.isNull(issueStatus) ? new ArrayList<>() : new ArrayList<>(issueStatus);
    }

    public List<Integer> getPcaFilterRatingMappings() {
        return Objects.isNull(pcaFilterRatingMappings) ? new ArrayList<>() : new ArrayList<>(pcaFilterRatingMappings);
    }

    public void setPcaFilterRatingMappings(List<Integer> pcaFilterRatingMappings) {
        this.pcaFilterRatingMappings = Objects.isNull(pcaFilterRatingMappings) ? new ArrayList<>() : new ArrayList<>(pcaFilterRatingMappings);
    }

    public List<Integer> getBondThemeList() {
        return Objects.isNull(bondThemeList) ? new ArrayList<>() : new ArrayList<>(bondThemeList);
    }

    public void setBondThemeList(List<Integer> bondThemeList) {
        this.bondThemeList = Objects.isNull(bondThemeList) ? new ArrayList<>() : new ArrayList<>(bondThemeList);
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getMinTbSpreadBp() {
        return minTbSpreadBp;
    }

    public void setMinTbSpreadBp(BigDecimal minTbSpreadBp) {
        this.minTbSpreadBp = minTbSpreadBp;
    }

    public BigDecimal getMaxTbSpreadBp() {
        return maxTbSpreadBp;
    }

    public void setMaxTbSpreadBp(BigDecimal maxTbSpreadBp) {
        this.maxTbSpreadBp = maxTbSpreadBp;
    }

    public Date getStartIssueEndDate() {
        return Objects.isNull(startIssueEndDate) ? null : new Date(startIssueEndDate.getTime());
    }

    public void setStartIssueEndDate(Date startIssueEndDate) {
        this.startIssueEndDate = Objects.isNull(startIssueEndDate) ? null : new Date(startIssueEndDate.getTime());
    }

    public Date getEndIssueEndDate() {
        return Objects.isNull(endIssueEndDate) ? null : new Date(endIssueEndDate.getTime());
    }

    public void setEndIssueEndDate(Date endIssueEndDate) {
        this.endIssueEndDate = Objects.isNull(endIssueEndDate) ? null : new Date(endIssueEndDate.getTime());
    }

    public String getStartTenor() {
        return startTenor;
    }

    public void setStartTenor(String startTenor) {
        this.startTenor = startTenor;
    }

    public Integer getStartTenorUnit() {
        return startTenorUnit;
    }

    public void setStartTenorUnit(Integer startTenorUnit) {
        this.startTenorUnit = startTenorUnit;
    }

    public String getEndTenor() {
        return endTenor;
    }

    public void setEndTenor(String endTenor) {
        this.endTenor = endTenor;
    }

    public Integer getEndTenorUnit() {
        return endTenorUnit;
    }

    public void setEndTenorUnit(Integer endTenorUnit) {
        this.endTenorUnit = endTenorUnit;
    }
}
