package com.innodealing.onshore.practice.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * 一级发行统计 —— 物化统计表实体（对应 bond_primary.bond_primary_stat_v2）。
 * <p>
 * 本实体作为 {@code GroupByQuery} 的“源类型”使用：WHERE / GROUP BY 的列都来自这里的 getter，
 * 由 mybatis-dynamic-query 按驼峰→下划线映射成数据库列名。
 * 聚合列（sum/count）不在这里声明，而是写在聚合结果实体 {@code BondPrimaryStatV2GroupDO} 的
 * {@link Column} 注解里——整个过程不依赖任何 XML。
 */
@Table(name = "bond_primary_stat_v2", schema = "bond_primary")
public class BondPrimaryStatV2DO {

    @Id
    @Column
    private Long bondUniCode;

    @Column
    private Integer deleted;

    /** 统计时间类型：0 起息日口径；1 到期日口径 */
    @Column
    private Integer statDateType;

    /** 债券类型（100/101 等伪码需由逻辑层展开为具体类型） */
    @Column
    private Integer bondFilterType;

    /** DMDC 原始债券类型（政策债细分场景使用） */
    @Column
    private Integer bondTypePar;

    @Column
    private Integer secondMarket;

    @Column
    private Integer publicOffering;

    @Column
    private Integer embeddedOption;

    @Column
    private Integer couponRateFilterType;

    /** 债券期限：天数 */
    @Column
    private Integer bondTenorDay;

    @Column
    private Integer bondTenorTag;

    @Column
    private Integer bondTenorDistribute;

    @Column
    private Integer crossMarketStatus;

    @Column
    private Integer comExtRatingFilterMapping;

    @Column
    private Integer bondExtRatingFilterMapping;

    @Column
    private Integer comListedStatus;

    @Column
    private Integer businessFilterNature;

    /** 板块分类：1 金融 2 产业 3 城投 4 类城投 99 其他 */
    @Column
    private Integer udicStatus;

    /** 国债利差(BP) */
    @Column
    private BigDecimal tbSpreadBp;

    /** 发行截止日 */
    @Column
    private Date issueEndDate;

    /** 公告日 */
    @Column
    private Date bulletinDate;

    /** 发行起始日 */
    @Column
    private Date issueStartDate;

    /** 缴款日（默认口径） */
    @Column
    private Date payDate;

    /** 上市日 */
    @Column
    private Date listedDate;

    /** 起息日 */
    @Column
    private Date interestStartDate;

    /** 招标日 */
    @Column
    private Date bidStartDate;

    /** 截标日 */
    @Column
    private Date bidEndDate;

    /** 实际到期日 */
    @Column
    private Date actualMaturityDate;

    @Column
    private Integer issueStatus;

    /** 实际发行金额 */
    @Column
    private BigDecimal actualIssueAmount;

    /** 发行次数（0 原债，>0 增发/续发） */
    @Column
    private Integer issueNum;

    /** 发行票息 = 实际发行金额 * 票面利率 */
    @Column
    private BigDecimal issueCoupon;

    /** 票面利息不为空的规模（用于加权票面分母） */
    @Column
    private BigDecimal issueAmountCoupon;

    /** 取消/删除规模 */
    @Column
    private BigDecimal deleteCancelAmount;

    /** 到期规模 */
    @Column
    private BigDecimal maturityAmount;

    /** 发行人编码 */
    @Column
    private Long comUniCode;

    @Column
    private Long provinceUniCode;

    @Column
    private Long cityUniCode;

    @Column
    private Long districtUniCode;

    /** 申万一级行业编码 */
    @Column
    private Long induLevel1Code;

    /** 申万二级行业编码 */
    @Column
    private Long induLevel2Code;

    /** DM 一级行业编码 */
    @Column
    private Long dmInduLevel1Code;

    /** DM 二级行业编码 */
    @Column
    private Long dmInduLevel2Code;

    /** 科创票据状态 0:不是 1:是 */
    @Column
    private Integer scienceTechNoteStatus;

    /** 科技创新公司债状态 0:不是 1:是 */
    @Column
    private Integer stiStatus;

    @Column
    private Integer tier1Status;

    @Column
    private Integer tier2Status;

    @Column
    private Integer corporateFinanceStatus;

    @Column
    private Integer nonBankFinanceStatus;

    @Column
    private Integer greenBondStatus;

    @Column
    private Integer carbonNeutralityStatus;

    @Column
    private Integer ruralRevivalStatus;

    @Column
    private Integer insurancePerpetualStatus;

    @Column
    private Integer pandaBondStatus;

    @Column
    private Integer highGrowthSectorBondStatus;

    public Long getBondUniCode() {
        return bondUniCode;
    }

    public void setBondUniCode(Long bondUniCode) {
        this.bondUniCode = bondUniCode;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getStatDateType() {
        return statDateType;
    }

    public void setStatDateType(Integer statDateType) {
        this.statDateType = statDateType;
    }

    public Integer getBondFilterType() {
        return bondFilterType;
    }

    public void setBondFilterType(Integer bondFilterType) {
        this.bondFilterType = bondFilterType;
    }

    public Integer getBondTypePar() {
        return bondTypePar;
    }

    public void setBondTypePar(Integer bondTypePar) {
        this.bondTypePar = bondTypePar;
    }

    public Integer getSecondMarket() {
        return secondMarket;
    }

    public void setSecondMarket(Integer secondMarket) {
        this.secondMarket = secondMarket;
    }

    public Integer getPublicOffering() {
        return publicOffering;
    }

    public void setPublicOffering(Integer publicOffering) {
        this.publicOffering = publicOffering;
    }

    public Integer getEmbeddedOption() {
        return embeddedOption;
    }

    public void setEmbeddedOption(Integer embeddedOption) {
        this.embeddedOption = embeddedOption;
    }

    public Integer getCouponRateFilterType() {
        return couponRateFilterType;
    }

    public void setCouponRateFilterType(Integer couponRateFilterType) {
        this.couponRateFilterType = couponRateFilterType;
    }

    public Integer getBondTenorDay() {
        return bondTenorDay;
    }

    public void setBondTenorDay(Integer bondTenorDay) {
        this.bondTenorDay = bondTenorDay;
    }

    public Integer getBondTenorTag() {
        return bondTenorTag;
    }

    public void setBondTenorTag(Integer bondTenorTag) {
        this.bondTenorTag = bondTenorTag;
    }

    public Integer getBondTenorDistribute() {
        return bondTenorDistribute;
    }

    public void setBondTenorDistribute(Integer bondTenorDistribute) {
        this.bondTenorDistribute = bondTenorDistribute;
    }

    public Integer getCrossMarketStatus() {
        return crossMarketStatus;
    }

    public void setCrossMarketStatus(Integer crossMarketStatus) {
        this.crossMarketStatus = crossMarketStatus;
    }

    public Integer getComExtRatingFilterMapping() {
        return comExtRatingFilterMapping;
    }

    public void setComExtRatingFilterMapping(Integer comExtRatingFilterMapping) {
        this.comExtRatingFilterMapping = comExtRatingFilterMapping;
    }

    public Integer getBondExtRatingFilterMapping() {
        return bondExtRatingFilterMapping;
    }

    public void setBondExtRatingFilterMapping(Integer bondExtRatingFilterMapping) {
        this.bondExtRatingFilterMapping = bondExtRatingFilterMapping;
    }

    public Integer getComListedStatus() {
        return comListedStatus;
    }

    public void setComListedStatus(Integer comListedStatus) {
        this.comListedStatus = comListedStatus;
    }

    public Integer getBusinessFilterNature() {
        return businessFilterNature;
    }

    public void setBusinessFilterNature(Integer businessFilterNature) {
        this.businessFilterNature = businessFilterNature;
    }

    public Integer getUdicStatus() {
        return udicStatus;
    }

    public void setUdicStatus(Integer udicStatus) {
        this.udicStatus = udicStatus;
    }

    public Integer getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(Integer issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Long getComUniCode() {
        return comUniCode;
    }

    public void setComUniCode(Long comUniCode) {
        this.comUniCode = comUniCode;
    }

    public Long getProvinceUniCode() {
        return provinceUniCode;
    }

    public void setProvinceUniCode(Long provinceUniCode) {
        this.provinceUniCode = provinceUniCode;
    }

    public Long getCityUniCode() {
        return cityUniCode;
    }

    public void setCityUniCode(Long cityUniCode) {
        this.cityUniCode = cityUniCode;
    }

    public Long getDistrictUniCode() {
        return districtUniCode;
    }

    public void setDistrictUniCode(Long districtUniCode) {
        this.districtUniCode = districtUniCode;
    }

    public Long getInduLevel1Code() {
        return induLevel1Code;
    }

    public void setInduLevel1Code(Long induLevel1Code) {
        this.induLevel1Code = induLevel1Code;
    }

    public Long getInduLevel2Code() {
        return induLevel2Code;
    }

    public void setInduLevel2Code(Long induLevel2Code) {
        this.induLevel2Code = induLevel2Code;
    }

    public Long getDmInduLevel1Code() {
        return dmInduLevel1Code;
    }

    public void setDmInduLevel1Code(Long dmInduLevel1Code) {
        this.dmInduLevel1Code = dmInduLevel1Code;
    }

    public Long getDmInduLevel2Code() {
        return dmInduLevel2Code;
    }

    public void setDmInduLevel2Code(Long dmInduLevel2Code) {
        this.dmInduLevel2Code = dmInduLevel2Code;
    }

    public BigDecimal getTbSpreadBp() {
        return tbSpreadBp;
    }

    public void setTbSpreadBp(BigDecimal tbSpreadBp) {
        this.tbSpreadBp = tbSpreadBp;
    }

    public Date getIssueEndDate() {
        return issueEndDate;
    }

    public void setIssueEndDate(Date issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    public Date getBulletinDate() {
        return bulletinDate;
    }

    public void setBulletinDate(Date bulletinDate) {
        this.bulletinDate = bulletinDate;
    }

    public Date getIssueStartDate() {
        return issueStartDate;
    }

    public void setIssueStartDate(Date issueStartDate) {
        this.issueStartDate = issueStartDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getListedDate() {
        return listedDate;
    }

    public void setListedDate(Date listedDate) {
        this.listedDate = listedDate;
    }

    public Date getInterestStartDate() {
        return interestStartDate;
    }

    public void setInterestStartDate(Date interestStartDate) {
        this.interestStartDate = interestStartDate;
    }

    public Date getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public Date getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Date getActualMaturityDate() {
        return actualMaturityDate;
    }

    public void setActualMaturityDate(Date actualMaturityDate) {
        this.actualMaturityDate = actualMaturityDate;
    }

    public BigDecimal getActualIssueAmount() {
        return actualIssueAmount;
    }

    public void setActualIssueAmount(BigDecimal actualIssueAmount) {
        this.actualIssueAmount = actualIssueAmount;
    }

    public Integer getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(Integer issueNum) {
        this.issueNum = issueNum;
    }

    public BigDecimal getIssueCoupon() {
        return issueCoupon;
    }

    public void setIssueCoupon(BigDecimal issueCoupon) {
        this.issueCoupon = issueCoupon;
    }

    public BigDecimal getIssueAmountCoupon() {
        return issueAmountCoupon;
    }

    public void setIssueAmountCoupon(BigDecimal issueAmountCoupon) {
        this.issueAmountCoupon = issueAmountCoupon;
    }

    public BigDecimal getDeleteCancelAmount() {
        return deleteCancelAmount;
    }

    public void setDeleteCancelAmount(BigDecimal deleteCancelAmount) {
        this.deleteCancelAmount = deleteCancelAmount;
    }

    public BigDecimal getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(BigDecimal maturityAmount) {
        this.maturityAmount = maturityAmount;
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

    public Integer getTier1Status() {
        return tier1Status;
    }

    public void setTier1Status(Integer tier1Status) {
        this.tier1Status = tier1Status;
    }

    public Integer getTier2Status() {
        return tier2Status;
    }

    public void setTier2Status(Integer tier2Status) {
        this.tier2Status = tier2Status;
    }

    public Integer getCorporateFinanceStatus() {
        return corporateFinanceStatus;
    }

    public void setCorporateFinanceStatus(Integer corporateFinanceStatus) {
        this.corporateFinanceStatus = corporateFinanceStatus;
    }

    public Integer getNonBankFinanceStatus() {
        return nonBankFinanceStatus;
    }

    public void setNonBankFinanceStatus(Integer nonBankFinanceStatus) {
        this.nonBankFinanceStatus = nonBankFinanceStatus;
    }

    public Integer getGreenBondStatus() {
        return greenBondStatus;
    }

    public void setGreenBondStatus(Integer greenBondStatus) {
        this.greenBondStatus = greenBondStatus;
    }

    public Integer getCarbonNeutralityStatus() {
        return carbonNeutralityStatus;
    }

    public void setCarbonNeutralityStatus(Integer carbonNeutralityStatus) {
        this.carbonNeutralityStatus = carbonNeutralityStatus;
    }

    public Integer getRuralRevivalStatus() {
        return ruralRevivalStatus;
    }

    public void setRuralRevivalStatus(Integer ruralRevivalStatus) {
        this.ruralRevivalStatus = ruralRevivalStatus;
    }

    public Integer getInsurancePerpetualStatus() {
        return insurancePerpetualStatus;
    }

    public void setInsurancePerpetualStatus(Integer insurancePerpetualStatus) {
        this.insurancePerpetualStatus = insurancePerpetualStatus;
    }

    public Integer getPandaBondStatus() {
        return pandaBondStatus;
    }

    public void setPandaBondStatus(Integer pandaBondStatus) {
        this.pandaBondStatus = pandaBondStatus;
    }

    public Integer getHighGrowthSectorBondStatus() {
        return highGrowthSectorBondStatus;
    }

    public void setHighGrowthSectorBondStatus(Integer highGrowthSectorBondStatus) {
        this.highGrowthSectorBondStatus = highGrowthSectorBondStatus;
    }
}
