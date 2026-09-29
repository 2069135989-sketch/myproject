package com.innodealing.onshore.practice.config.serializer;

import com.innodealing.onshore.bondmetadata.json.serializer.DisplayNumberJsonWithFormatSerializer;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * 发行人特殊展示序列化
 */
public class ComDisplayNumberJsonSerializer extends DisplayNumberJsonWithFormatSerializer {

    public String getDisplayString(final Number number) {
        if (Objects.isNull(number)) {
            return EMPTY_PLACEHOLDER;
        }
        if (StringUtils.isBlank(numberPattern)) {
            numberPattern = "0.00##";
        }
        DecimalFormat df = new DecimalFormat(numberPattern);
        return df.format(number);
    }
}
