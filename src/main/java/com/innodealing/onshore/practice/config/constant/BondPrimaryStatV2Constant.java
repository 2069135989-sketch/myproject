package com.innodealing.onshore.practice.config.constant;

/**
 * 一级发行统计相关常量。
 * <p>
 * 与原项目 {@code PgBondPrimaryStatsV2Constant.BASE_BOND_PRIMARY_STAT_TABLE} 一致，
 * 这里只保留本接口用到的“基础统计表”表名。
 */
public final class BondPrimaryStatV2Constant {

    private BondPrimaryStatV2Constant() {

    }

    /**
     * 基础统计物化表（总量/分组聚合的统一数据源，schema.table 形式）。
     */
    public static final String BASE_BOND_PRIMARY_STAT_TABLE =
            "bond_primary.bond_primary_stat_v2";
}
