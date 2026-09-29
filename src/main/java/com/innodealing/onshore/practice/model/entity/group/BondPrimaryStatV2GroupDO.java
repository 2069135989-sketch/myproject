package com.innodealing.onshore.practice.model.entity.group;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * 基础统计表(bond_primary.bond_primary_stat_v2)聚合结果 DO。
 * <p>
 * 与原项目 {@code MvBaseStatsV2GroupDO} 一致：聚合列通过 {@link Column#name()} 直接写 SQL 表达式，
 * 由 {@code GroupedQuerySqlHelper.getSelectColumnsClause()} 在运行时读取并拼进 SELECT。
 * 例如：
 * <pre>
 *   sum(actual_issue_amount) FILTER (WHERE stat_date_type = 0 AND issue_status = 1)
 * </pre>
 * 表示“起息日口径 + 已上市”的发行额；FILTER 语法为 PostgreSQL / PolarDB 所支持。
 */
public class BondPrimaryStatV2GroupDO {

    @Column
    private Date bulletinDate;
    @Column
    private Date issueStartDate;
    @Column
    private Date issueEndDate;
    @Column
    private Date payDate;
    @Column
    private Date listedDate;
    @Column
    private Date interestStartDate;
    @Column
    private Date bidStartDate;
    @Column
    private Date bidEndDate;
    @Column
    private Date actualMaturityDate;

    @Column
    private Date statDateMonday;

    @Column
    private Long statDateMonth;

    @Column
    private Long statDateQuarter;

    @Column
    private Long statDateYear;

    @Column
    private Long provinceUniCode;

    @Column
    private Long cityUniCode;

    @Column
    private Long districtUniCode;

    @Column
    private Long induLevel1Code;

    @Column
    private Long induLevel2Code;

    @Column
    private Long dmInduLevel1Code;

    @Column
    private Long dmInduLevel2Code;

    @Column
    private Long dmInduLevel3Code;

    @Column
    private Long comUniCode;

    @Column
    private Integer bondTenorTag;

    @Column
    private Integer bondTenorDistribute;

    @Column
    private Integer bondExtRatingFilterMapping;

    /** 发行票息合计 = sum(issue_coupon) FILTER (WHERE stat_date_type = 0 AND issue_status = 1) */
    @Column(name = "sum(issue_coupon) FILTER (WHERE stat_date_type = 0 AND issue_status = 1)")
    private BigDecimal issueCoupon;

    /** 区间发行 = sum(actual_issue_amount) FILTER (WHERE stat_date_type = 0 AND issue_status = 1) */
    @Column(name = "sum(actual_issue_amount) FILTER (WHERE stat_date_type = 0 AND issue_status = 1)")
    private BigDecimal issueAmount;

    /** 推迟/取消发行 = sum(actual_issue_amount) FILTER (WHERE stat_date_type = 0 AND issue_status IN (2, 3)) */
    @Column(name = "sum(actual_issue_amount) FILTER (WHERE stat_date_type = 0 AND issue_status IN (2, 3))")
    private BigDecimal deleteCancelAmount;

    /** 发行数量 = count(1) FILTER (WHERE stat_date_type = 0 AND issue_status = 1) */
    @Column(name = "count(1) FILTER (WHERE stat_date_type = 0 AND issue_status = 1)")
    private Integer issueNum;

    /** 区间到期 = sum(actual_issue_amount) FILTER (WHERE stat_date_type = 1 AND issue_status = 1) */
    @Column(name = "sum(actual_issue_amount) FILTER (WHERE stat_date_type = 1 AND issue_status = 1)")
    private BigDecimal maturityAmount;

    /** 加权票面分母 = sum(actual_issue_amount) FILTER (WHERE stat_date_type = 0 AND issue_coupon IS NOT NULL AND issue_status = 1) */
    @Column(name = "sum(actual_issue_amount) FILTER (WHERE stat_date_type = 0 AND issue_coupon IS NOT NULL AND issue_status = 1)")
    private BigDecimal issueAmountCoupon;

    public Date getBulletinDate() { return copyDate(bulletinDate); }
    public void setBulletinDate(Date value) { bulletinDate = copyDate(value); }
    public Date getIssueStartDate() { return copyDate(issueStartDate); }
    public void setIssueStartDate(Date value) { issueStartDate = copyDate(value); }
    public Date getIssueEndDate() { return copyDate(issueEndDate); }
    public void setIssueEndDate(Date value) { issueEndDate = copyDate(value); }
    public Date getPayDate() { return copyDate(payDate); }
    public void setPayDate(Date value) { payDate = copyDate(value); }
    public Date getListedDate() { return copyDate(listedDate); }
    public void setListedDate(Date value) { listedDate = copyDate(value); }
    public Date getInterestStartDate() { return copyDate(interestStartDate); }
    public void setInterestStartDate(Date value) { interestStartDate = copyDate(value); }
    public Date getBidStartDate() { return copyDate(bidStartDate); }
    public void setBidStartDate(Date value) { bidStartDate = copyDate(value); }
    public Date getBidEndDate() { return copyDate(bidEndDate); }
    public void setBidEndDate(Date value) { bidEndDate = copyDate(value); }
    public Date getActualMaturityDate() { return copyDate(actualMaturityDate); }
    public void setActualMaturityDate(Date value) { actualMaturityDate = copyDate(value); }

    private Date copyDate(Date value) {
        return value == null ? null : new Date(value.getTime());
    }

    public Date getStatDateMonday() {
        return Objects.isNull(statDateMonday) ? null : new Date(statDateMonday.getTime());
    }
    public void setStatDateMonday(Date statDateMonday) {
        this.statDateMonday = Objects.isNull(statDateMonday) ? null : new Date(statDateMonday.getTime());
    }
    public Long getStatDateMonth() {
        return statDateMonth;
    }
    public void setStatDateMonth(Long statDateMonth) {
        this.statDateMonth = statDateMonth;
    }
    public Long getStatDateQuarter() {
        return statDateQuarter;
    }
    public void setStatDateQuarter(Long statDateQuarter) {
        this.statDateQuarter = statDateQuarter;
    }
    public Long getStatDateYear() {
        return statDateYear;
    }
    public void setStatDateYear(Long statDateYear) {
        this.statDateYear = statDateYear;
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
    public Long getDmInduLevel3Code() {
        return dmInduLevel3Code;
    }
    public void setDmInduLevel3Code(Long dmInduLevel3Code) {
        this.dmInduLevel3Code = dmInduLevel3Code;
    }
    public Long getComUniCode() {
        return comUniCode;
    }
    public void setComUniCode(Long comUniCode) {
        this.comUniCode = comUniCode;
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
    public Integer getBondExtRatingFilterMapping() {
        return bondExtRatingFilterMapping;
    }
    public void setBondExtRatingFilterMapping(Integer bondExtRatingFilterMapping) {
        this.bondExtRatingFilterMapping = bondExtRatingFilterMapping;
    }
    public BigDecimal getIssueCoupon() {
        return issueCoupon;
    }
    public void setIssueCoupon(BigDecimal issueCoupon) {
        this.issueCoupon = issueCoupon;
    }
    public BigDecimal getIssueAmount() {
        return issueAmount;
    }
    public void setIssueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
    }
    public BigDecimal getDeleteCancelAmount() {
        return deleteCancelAmount;
    }
    public void setDeleteCancelAmount(BigDecimal deleteCancelAmount) {
        this.deleteCancelAmount = deleteCancelAmount;
    }
    public Integer getIssueNum() {
        return issueNum;
    }
    public void setIssueNum(Integer issueNum) {
        this.issueNum = issueNum;
    }
    public BigDecimal getMaturityAmount() {
        return maturityAmount;
    }
    public void setMaturityAmount(BigDecimal maturityAmount) {
        this.maturityAmount = maturityAmount;
    }
    public BigDecimal getIssueAmountCoupon() {
        return issueAmountCoupon;
    }
    public void setIssueAmountCoupon(BigDecimal issueAmountCoupon) {
        this.issueAmountCoupon = issueAmountCoupon;
    }
}
