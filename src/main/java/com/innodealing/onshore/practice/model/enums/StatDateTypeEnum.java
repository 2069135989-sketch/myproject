package com.innodealing.onshore.practice.model.enums;

/**
 * 统计时间类型（与原项目 StatDateTypeEnum 一致）。
 * <p>
 * 物化统计表中同一笔债券会按“起息日口径”和“到期日口径”各存一行，
 * 通过 stat_date_type 区分：0=起息日（发行侧），1=到期日（到期侧）。
 * 聚合列的 FILTER (WHERE stat_date_type = ...) 正是依赖这个枚举。
 */
public enum StatDateTypeEnum {

    INTEREST_DATE(0, "起息日"),
    MATURITY_DATE(1, "到期日");

    private final int value;
    private final String text;

    StatDateTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
