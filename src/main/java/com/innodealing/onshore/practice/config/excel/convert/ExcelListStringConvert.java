package com.innodealing.onshore.practice.config.excel.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.google.common.base.Joiner;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 时间转换器
 *
 * @author caodz
 * @date 2022/10/18 13:48
 */
public class ExcelListStringConvert implements Converter<List<String>> {

    @Override
    public Class supportJavaTypeKey() {
        return List.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public List<String> convertToJavaData(ReadCellData<?> cellData,
                                          ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();
        if (StringUtils.isBlank(stringValue)) {
            return Collections.emptyList();
        }
        return Arrays.stream(stringValue.split(",")).collect(Collectors.toList());
    }

    @Override
    public WriteCellData<?> convertToExcelData(List<String> strings, ExcelContentProperty excelContentProperty,
                                               GlobalConfiguration globalConfiguration) {
        if (CollectionUtils.isEmpty(strings)) {
            return new WriteCellData("");
        }
        return new WriteCellData(Joiner.on(",").join(strings));
    }
}
