package com.innodealing.onshore.practice.util;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 净融资金额工具（与原项目 NetFinancingAmountUtils 一致）。
 * <p>
 * 净融资额 = 发行额 - 到期额，空值按 0 处理。
 */
public final class NetFinancingAmountUtils {

    private NetFinancingAmountUtils() {
    }

    public static BigDecimal calculate(BigDecimal issueAmount, BigDecimal maturityAmount) {
        BigDecimal effectiveIssueAmount = Objects.nonNull(issueAmount) ? issueAmount : BigDecimal.ZERO;
        BigDecimal effectiveMaturityAmount = Objects.nonNull(maturityAmount) ? maturityAmount : BigDecimal.ZERO;
        return effectiveIssueAmount.subtract(effectiveMaturityAmount);
    }
}
