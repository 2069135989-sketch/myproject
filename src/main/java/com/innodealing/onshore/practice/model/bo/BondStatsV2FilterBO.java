package com.innodealing.onshore.practice.model.bo;


import com.innodealing.onshore.practice.model.enums.PrimaryBondFilterDateEnum;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 一级统计过滤类
 *
 **/
public class BondStatsV2FilterBO {

    /**
     * 开始时间
     **/
    private Date startDate;

    /**
     * 结束时间
     **/
    private Date endDate;

    /**
     * 日期类型；与信用债一级发行口径一致，默认缴款日。
     **/
    private Integer dateType = PrimaryBondFilterDateEnum.PAY_DATE.getValue();

    /**
     * 债券类型
     **/
    private List<Integer> bondFilterTypes;

    /** 政策债细分使用的发行人编码。 */
    private List<Long> bondTypeComUniCodes;

    /**
     * 交易场所： 1 深圳证券交易所;2 上海证券交易所; 3  银行间市场;4  柜台交易市场; 99 其他
     **/
    private List<Integer> secondMarkets;

    /**
     * 募集方式： 0： 私募； 1：公募
     **/
    private Integer publicOffering;

    /**
     * 发行条款： 0:含权 1：不含权 2:永续
     **/
    private List<Integer> embeddedOptions;

    /**
     * 1：固息; 2 浮息; 999：其他
     **/
    private Integer couponRateFilterType;
    /**
     * 跨市场： 0 否； 1：是
     **/
    private Integer crossMarketStatus;
    /**
     * 债券外部评级
     **/
    private List<Integer> bondExtRatingFilterMappings;
    /**
     * 主体外部评级
     **/
    private List<Integer> comExtRatingFilterMappings;
    /**
     * 发行人是否上市： 0: 否； 1： 是
     **/
    private Integer comListedStatus;

    /**
     * 企业性质：详细枚举值见字典
     **/
    private List<Integer> businessFilterNatures;

    /**
     * 是否城投列表: 1 金融 2 产业 3 城投 4 类城投 99 其他
     **/
    private List<Integer> udicStatusList;

    /**
     * 债券发行状态：0:  发行中; 1:  已经上市; 2:  延迟发行; 3:  取消发行 10: 到期
     **/
    private List<Integer> issueStatus;
    /**
     * 债券期限
     */
    private List<Integer> tenorTags;

    /**
     * 开始期限
     */
    private String startTenor;

    /**
     * 开始期限单位：1-日  2-月  3-年
     */
    private Integer startTenorUnit;

    /**
     * 结束期限
     */
    private String endTenor;

    /**
     * 结束期限单位：1-日  2-月  3-年
     */
    private Integer endTenorUnit;
    /**
     * 省份编码
     */
    private List<Long> provinceUniCodes;
    /**
     * 城市编码
     */
    private List<Long> cityUniCodes;
    /**
     * 区编码
     */
    private List<Long> districtUniCodes;
    /**
     * 行业一级编码
     */
    private List<Long> induLevel1Codes;
    /**
     * 行业二级编码
     */
    private List<Long> induLevel2Codes;
    /**
     * 行业口径：1 申万（默认） 2 DM行业
     */
    private Integer industryStandard;

    /**
     * 标普PCA评分筛选项映射，1 aaa系列;  2 aa系列;  3 a系列; 4 bbb系列；5 bb系列；6 b系列；7 ccc系列及以下；999:其他
     */
    private List<Integer> pcaFilterRatingMappings;

    /**
     * 科创票据状态  0:不是   1:是
     */
    private Integer scienceTechNoteStatus;

    /**
     * 科技创新公司债状态  0:不是   1:是
     */
    private Integer stiStatus;

    private Integer tier1Status;
    private Integer tier2Status;
    private Integer corporateFinanceStatus;
    private Integer nonBankFinanceStatus;
    private Integer greenBondStatus;
    private Integer carbonNeutralityStatus;
    private Integer ruralRevivalStatus;
    private Integer insurancePerpetualStatus;
    private Integer pandaBondStatus;
    private Integer highGrowthSectorBondStatus;

    /**
     * 关注组解析后的发行主体编码列表
     */
    private List<Long> comUniCodes;

    /**
     * 最小国债利差BP
     */
    private BigDecimal minTbSpreadBp;

    /**
     * 最大国债利差BP
     */
    private BigDecimal maxTbSpreadBp;

    /**
     * 发行截止日-开始
     */
    private Date startIssueEndDate;

    /**
     * 发行截止日-结束
     */
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

    public List<Long> getBondTypeComUniCodes() {
        return Objects.isNull(bondTypeComUniCodes)
                ? new ArrayList<>() : new ArrayList<>(bondTypeComUniCodes);
    }

    public void setBondTypeComUniCodes(List<Long> bondTypeComUniCodes) {
        this.bondTypeComUniCodes = Objects.isNull(bondTypeComUniCodes)
                ? new ArrayList<>() : new ArrayList<>(bondTypeComUniCodes);
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

    public List<Integer> getBondExtRatingFilterMappings() {
        return Objects.isNull(bondExtRatingFilterMappings) ? new ArrayList<>() : new ArrayList<>(bondExtRatingFilterMappings);
    }

    public void setBondExtRatingFilterMappings(List<Integer> bondExtRatingFilterMappings) {
        this.bondExtRatingFilterMappings = Objects.isNull(bondExtRatingFilterMappings) ?
                new ArrayList<>() : new ArrayList<>(bondExtRatingFilterMappings);
    }

    public List<Integer> getComExtRatingFilterMappings() {
        return Objects.isNull(comExtRatingFilterMappings) ? new ArrayList<>() : new ArrayList<>(comExtRatingFilterMappings);
    }

    public void setComExtRatingFilterMappings(List<Integer> comExtRatingFilterMappings) {
        this.comExtRatingFilterMappings = Objects.isNull(comExtRatingFilterMappings) ?
                new ArrayList<>() : new ArrayList<>(comExtRatingFilterMappings);
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

    public List<Integer> getTenorTags() {
        return Objects.isNull(tenorTags) ? new ArrayList<>() : new ArrayList<>(tenorTags);
    }

    public void setTenorTags(List<Integer> tenorTags) {
        this.tenorTags = Objects.isNull(tenorTags) ? new ArrayList<>() : new ArrayList<>(tenorTags);
    }

    public List<Long> getProvinceUniCodes() {
        return Objects.isNull(provinceUniCodes) ? new ArrayList<>() : new ArrayList<>(provinceUniCodes);
    }

    public void setProvinceUniCodes(List<Long> provinceUniCodes) {
        this.provinceUniCodes = Objects.isNull(provinceUniCodes) ? new ArrayList<>() : new ArrayList<>(provinceUniCodes);
    }

    public List<Long> getCityUniCodes() {
        return Objects.isNull(cityUniCodes) ? new ArrayList<>() : new ArrayList<>(cityUniCodes);
    }

    public void setCityUniCodes(List<Long> cityUniCodes) {
        this.cityUniCodes = Objects.isNull(cityUniCodes) ? new ArrayList<>() : new ArrayList<>(cityUniCodes);
    }

    public List<Long> getDistrictUniCodes() {
        return Objects.isNull(districtUniCodes) ? new ArrayList<>() : new ArrayList<>(districtUniCodes);
    }

    public void setDistrictUniCodes(List<Long> districtUniCodes) {
        this.districtUniCodes = Objects.isNull(districtUniCodes) ? new ArrayList<>() : new ArrayList<>(districtUniCodes);
    }

    public List<Long> getInduLevel1Codes() {
        return Objects.isNull(induLevel1Codes) ? new ArrayList<>() : new ArrayList<>(induLevel1Codes);
    }

    public void setInduLevel1Codes(List<Long> induLevel1Codes) {
        this.induLevel1Codes = Objects.isNull(induLevel1Codes) ? new ArrayList<>() : new ArrayList<>(induLevel1Codes);
    }

    public List<Long> getInduLevel2Codes() {
        return Objects.isNull(induLevel2Codes) ? new ArrayList<>() : new ArrayList<>(induLevel2Codes);
    }

    public void setInduLevel2Codes(List<Long> induLevel2Codes) {
        this.induLevel2Codes = Objects.isNull(induLevel2Codes) ? new ArrayList<>() : new ArrayList<>(induLevel2Codes);
    }

    public Integer getIndustryStandard() {
        return industryStandard;
    }

    public void setIndustryStandard(Integer industryStandard) {
        this.industryStandard = industryStandard;
    }

    public List<Integer> getPcaFilterRatingMappings() {
        return Objects.isNull(pcaFilterRatingMappings) ? new ArrayList<>() : new ArrayList<>(pcaFilterRatingMappings);
    }

    public void setPcaFilterRatingMappings(List<Integer> pcaFilterRatingMappings) {
        this.pcaFilterRatingMappings = Objects.isNull(pcaFilterRatingMappings) ? new ArrayList<>() : new ArrayList<>(pcaFilterRatingMappings);
    }

    public Integer getScienceTechNoteStatus() {
        return scienceTechNoteStatus;
    }

    public void setScienceTechNoteStatus(Integer scienceTechNoteStatus) {
        this.scienceTechNoteStatus = scienceTechNoteStatus;
    }

    public Integer getStiStatus() {
        return stiStatus;
    }

    public void setStiStatus(Integer stiStatus) {
        this.stiStatus = stiStatus;
    }

    public List<Long> getComUniCodes() {
        return comUniCodes;
    }

    public void setComUniCodes(List<Long> comUniCodes) {
        this.comUniCodes = comUniCodes;
    }

    public Integer getTier1Status() { return tier1Status; }
    public void setTier1Status(Integer tier1Status) { this.tier1Status = tier1Status; }
    public Integer getTier2Status() { return tier2Status; }
    public void setTier2Status(Integer tier2Status) { this.tier2Status = tier2Status; }
    public Integer getCorporateFinanceStatus() { return corporateFinanceStatus; }
    public void setCorporateFinanceStatus(Integer corporateFinanceStatus) { this.corporateFinanceStatus = corporateFinanceStatus; }
    public Integer getNonBankFinanceStatus() { return nonBankFinanceStatus; }
    public void setNonBankFinanceStatus(Integer nonBankFinanceStatus) { this.nonBankFinanceStatus = nonBankFinanceStatus; }
    public Integer getGreenBondStatus() { return greenBondStatus; }
    public void setGreenBondStatus(Integer greenBondStatus) { this.greenBondStatus = greenBondStatus; }
    public Integer getCarbonNeutralityStatus() { return carbonNeutralityStatus; }
    public void setCarbonNeutralityStatus(Integer carbonNeutralityStatus) { this.carbonNeutralityStatus = carbonNeutralityStatus; }
    public Integer getRuralRevivalStatus() { return ruralRevivalStatus; }
    public void setRuralRevivalStatus(Integer ruralRevivalStatus) { this.ruralRevivalStatus = ruralRevivalStatus; }
    public Integer getInsurancePerpetualStatus() { return insurancePerpetualStatus; }
    public void setInsurancePerpetualStatus(Integer insurancePerpetualStatus) { this.insurancePerpetualStatus = insurancePerpetualStatus; }
    public Integer getPandaBondStatus() { return pandaBondStatus; }
    public void setPandaBondStatus(Integer pandaBondStatus) { this.pandaBondStatus = pandaBondStatus; }
    public Integer getHighGrowthSectorBondStatus() { return highGrowthSectorBondStatus; }
    public void setHighGrowthSectorBondStatus(Integer highGrowthSectorBondStatus) { this.highGrowthSectorBondStatus = highGrowthSectorBondStatus; }

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
