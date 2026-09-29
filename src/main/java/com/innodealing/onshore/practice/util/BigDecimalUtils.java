package com.innodealing.onshore.practice.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

/**
 * BigDecimal 工具（练习版，仅保留本接口用到的 safeDivide，口径与原项目一致）。
 * <p>
 * safeDivide(num, den, roundingMode)：分母为空或 0 时返回 {@link Optional#empty()}，
 * 否则返回 num/den（精度 4，四舍五入）。
 */
public final class BigDecimalUtils {

    /** 计算精度为 4 */
    private static final int SCALE = 4;

    private BigDecimalUtils() {
    }

    public static Optional<BigDecimal> safeDivide(BigDecimal numerator, BigDecimal denominator, RoundingMode roundingMode) {
        if (Objects.isNull(numerator) || Objects.isNull(denominator)
                || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return Optional.empty();
        }
        return Optional.of(numerator.divide(denominator, SCALE, roundingMode));
    }
}
