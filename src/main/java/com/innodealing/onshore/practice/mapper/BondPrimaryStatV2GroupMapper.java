package com.innodealing.onshore.practice.mapper;

import com.innodealing.onshore.practice.model.entity.BondPrimaryStatV2DO;
import com.innodealing.onshore.practice.model.entity.group.BondPrimaryStatV2GroupDO;

/**
 * 一级发行统计 —— 分组聚合 Mapper。
 * <p>
 * 与原项目 {@code MvBaseStatsV2GroupMapper} 一致：继承 {@link SelectByGroupedExtQueryMapper}，
 * 泛型 Q = 源实体（统计表），S = 聚合结果实体（{@link BondPrimaryStatV2GroupDO}）。
 * 不写任何 XML，所有 SQL 由 {@code GroupedExtQueryProvider} 运行时拼装。
 */
public interface BondPrimaryStatV2GroupMapper
        extends SelectByGroupedExtQueryMapper<BondPrimaryStatV2DO, BondPrimaryStatV2GroupDO> {
}
