package com.innodealing.onshore.practice.service;


import com.innodealing.onshore.practice.model.dto.request.BondStatsV2RequestDTO;
import com.innodealing.onshore.practice.model.dto.response.BaseBondStatsV2AmountDTO;

/**
 * 一级发行统计
 *
 */
public interface BondPrimaryStatV2Service {
    /**
     * 一级统计总量
     *
     * @param requestDTO 请求参数
     * @return BondStatsV2AmountCodeResponseDTO
     */
    BaseBondStatsV2AmountDTO listBondAmountStats(BondStatsV2RequestDTO requestDTO);

}
