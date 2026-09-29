package com.innodealing.onshore.practice.model.enums;

import com.innodealing.onshore.bondmetadata.enums.ITextValueEnum;

/**
 * 一级发行新债发行 日期过滤枚举
 */
public enum PrimaryBondFilterDateEnum implements ITextValueEnum {

    PUBLISH_DATE(1, "公告日"),
    ISSUE_START_DATE(2, "发行起始日"),
    ISSUE_END_DATE(3, "发行截止日"),
    PAY_DATE(4, "缴款日"),
    LISTED_DATE(5, "上市日"),
    INTEREST_START_DATE(6, "起息日"),
    BID_START_DATE(7, "招标日"),
    BID_END_DATE(8, "截标日"),
    ACTUAL_MATURITY_DATE(9, "到期日"),
    ;

    private final int value;
    private final String text;

    PrimaryBondFilterDateEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int getValue() {
        return value;
    }

}
