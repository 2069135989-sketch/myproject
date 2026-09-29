package com.innodealing.onshore.practice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.innodealing.onshore.bondmetadata.json.serializer.DisplayNumberJsonWithFormatSerializer;
import com.innodealing.onshore.practice.config.serializer.ComDisplayNumberJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 债券： 一级发行金融统计DTO
 *
 * @Author lxw
 * @Date 2026/9/28
 **/
public class BaseBondStatsV2AmountDTO {

    @ApiModelProperty("区间发行")
    @JsonSerialize(using = DisplayNumberJsonWithFormatSerializer.class, nullsUsing = DisplayNumberJsonWithFormatSerializer.class)
    @JsonFormat(pattern = "##,###,##0.00")
    private BigDecimal actualIssueAmount;

    @ApiModelProperty("发行数量")
    @JsonSerialize(using = DisplayNumberJsonWithFormatSerializer.class, nullsUsing = DisplayNumberJsonWithFormatSerializer.class)
    @JsonFormat(pattern = "##,###,###")
    private Integer issueNum;

    @ApiModelProperty("加权平均票面")
    @JsonSerialize(using = DisplayNumberJsonWithFormatSerializer.class, nullsUsing = DisplayNumberJsonWithFormatSerializer.class)
    @JsonFormat(pattern = "0.0000")
    private BigDecimal avgIssueCoupon;

    @ApiModelProperty("区间到期")
    @JsonSerialize(using = DisplayNumberJsonWithFormatSerializer.class, nullsUsing = DisplayNumberJsonWithFormatSerializer.class)
    @JsonFormat(pattern = "##,###,##0.00")
    private BigDecimal maturityAmount;

    @ApiModelProperty("区间净融资额")
    @JsonSerialize(using = ComDisplayNumberJsonSerializer.class, nullsUsing = ComDisplayNumberJsonSerializer.class)
    @JsonFormat(pattern = "##,###,##0.00")
    private BigDecimal netFinancingAmount;

    @ApiModelProperty("推迟/取消发行")
    @JsonSerialize(using = DisplayNumberJsonWithFormatSerializer.class, nullsUsing = DisplayNumberJsonWithFormatSerializer.class)
    @JsonFormat(pattern = "##,###,##0.00")
    private BigDecimal delayOrCancelAmount;

    @ApiModelProperty("占发行总额")
    @JsonSerialize(using = DisplayNumberJsonWithFormatSerializer.class, nullsUsing = DisplayNumberJsonWithFormatSerializer.class)
    @JsonFormat(pattern = "0.0000")
    private BigDecimal totalIssueAmountRatio;

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

    public BigDecimal getAvgIssueCoupon() {
        return avgIssueCoupon;
    }

    public void setAvgIssueCoupon(BigDecimal avgIssueCoupon) {
        this.avgIssueCoupon = avgIssueCoupon;
    }

    public BigDecimal getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(BigDecimal maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public BigDecimal getNetFinancingAmount() {
        return netFinancingAmount;
    }

    public void setNetFinancingAmount(BigDecimal netFinancingAmount) {
        this.netFinancingAmount = netFinancingAmount;
    }

    public BigDecimal getDelayOrCancelAmount() {
        return delayOrCancelAmount;
    }

    public void setDelayOrCancelAmount(BigDecimal delayOrCancelAmount) {
        this.delayOrCancelAmount = delayOrCancelAmount;
    }

    public BigDecimal getTotalIssueAmountRatio() {
        return totalIssueAmountRatio;
    }

    public void setTotalIssueAmountRatio(BigDecimal totalIssueAmountRatio) {
        this.totalIssueAmountRatio = totalIssueAmountRatio;
    }
}
