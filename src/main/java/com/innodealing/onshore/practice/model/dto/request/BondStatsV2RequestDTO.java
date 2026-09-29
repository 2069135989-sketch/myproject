package com.innodealing.onshore.practice.model.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 债券： 一级发行统计DTO
 *
 * @Author lxw
 * @Date 2026/9/28
 **/
public class BondStatsV2RequestDTO extends BaseBondStatsV2RequestDTO {

    @ApiModelProperty("省份编码；-1 表示其他（同时包含数据库 0 和 NULL），0 为无效值")
    private List<Long> provinceUniCodes;

    @ApiModelProperty("城市编码；-1 表示其他（同时包含数据库 0 和 NULL），0 为无效值")
    private List<Long> cityUniCodes;

    @ApiModelProperty("区县编码；-1 表示其他（同时包含数据库 0 和 NULL），0 为无效值")
    private List<Long> districtUniCodes;

    @ApiModelProperty("行业一级编码；0 表示其他（同时包含数据库 0 和 NULL）")
    private List<Long> induLevel1Codes;

    @ApiModelProperty("行业二级编码；0 表示其他（同时包含数据库 0 和 NULL）")
    private List<Long> induLevel2Codes;

    @ApiModelProperty("行业口径：1 申万（默认，不传按申万）；2 DM行业。决定 induLevel1Codes/induLevel2Codes 与分组维度生效的列")
    private Integer industryStandard;

    public void setProvinceUniCodes(List<Long> provinceUniCodes) {
        this.provinceUniCodes = Objects.isNull(provinceUniCodes) ? new ArrayList<>() : new ArrayList<>(provinceUniCodes);
    }

    public List<Long> getProvinceUniCodes() {
        return Objects.isNull(provinceUniCodes) ? new ArrayList<>() : new ArrayList<>(provinceUniCodes);
    }

    public void setCityUniCodes(List<Long> cityUniCodes) {
        this.cityUniCodes = Objects.isNull(cityUniCodes) ? new ArrayList<>() : new ArrayList<>(cityUniCodes);
    }

    public List<Long> getCityUniCodes() {
        return Objects.isNull(cityUniCodes) ? new ArrayList<>() : new ArrayList<>(cityUniCodes);
    }

    public void setDistrictUniCodes(List<Long> districtUniCodes) {
        this.districtUniCodes = Objects.isNull(districtUniCodes) ? new ArrayList<>() : new ArrayList<>(districtUniCodes);
    }

    public List<Long> getDistrictUniCodes() {
        return Objects.isNull(districtUniCodes) ? new ArrayList<>() : new ArrayList<>(districtUniCodes);
    }

    public void setInduLevel1Codes(List<Long> induLevel1Codes) {
        this.induLevel1Codes = Objects.isNull(induLevel1Codes) ? new ArrayList<>() : new ArrayList<>(induLevel1Codes);
    }

    public List<Long> getInduLevel1Codes() {
        return Objects.isNull(induLevel1Codes) ? new ArrayList<>() : new ArrayList<>(induLevel1Codes);
    }

    public void setInduLevel2Codes(List<Long> induLevel2Codes) {
        this.induLevel2Codes = Objects.isNull(induLevel2Codes) ? new ArrayList<>() : new ArrayList<>(induLevel2Codes);
    }

    public List<Long> getInduLevel2Codes() {
        return Objects.isNull(induLevel2Codes) ? new ArrayList<>() : new ArrayList<>(induLevel2Codes);
    }

    public void setIndustryStandard(Integer industryStandard) {
        this.industryStandard = industryStandard;
    }

    public Integer getIndustryStandard() {
        return industryStandard;
    }
}
