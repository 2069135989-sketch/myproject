package com.innodealing.onshore.practice.controller;

import com.innodealing.commons.http.RestResponse;
import com.innodealing.onshore.practice.model.dto.request.BondStatsV2RequestDTO;
import com.innodealing.onshore.practice.model.dto.response.BaseBondStatsV2AmountDTO;
import com.innodealing.onshore.practice.service.BondPrimaryStatV2Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 一级统计
 *
 */
@ApiOperation(value = "一级统计")
@RestController("/api/bond/primary")
public class BondStatsController {

    @Resource
    BondPrimaryStatV2Service bondPrimaryStatV2Service;

    @ApiOperation(value = "一级总量统计")
    @RequestMapping("/stats/v2/amount/total")
    public RestResponse<BaseBondStatsV2AmountDTO> listBondAmountStats
            (@CookieValue(name = "userid", required = false) Long userid,
             BondStatsV2RequestDTO bondStatsV2RequestDTO) {
       bondStatsV2RequestDTO.setUserId(userid);
       return RestResponse.Success(bondPrimaryStatV2Service.listBondAmountStats(bondStatsV2RequestDTO));

    }
}
