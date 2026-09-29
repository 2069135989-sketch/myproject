package com.innodealing.onshore.practice.config.excel.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.innodealing.commons.object.DateExtensionUtils;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * 时间转换器
 *
 * @author wangshuaishuai
 * @date 2022/09/04 13:48
 */
public class ExcelDateConvert implements Converter<Date> {

    private static final Integer CALENDER_BASE_YEAR = 1900;
    private static final Integer CALENDER_BASE_MONTH = -1;

    @Override
    public Class supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Date convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) throws Exception {
        Calendar calendar = new GregorianCalendar(CALENDER_BASE_YEAR, 0, CALENDER_BASE_MONTH);
        if (Objects.equals(cellData.getType(), CellDataTypeEnum.NUMBER)) {
            calendar.add(Calendar.DATE, cellData.getNumberValue().intValue());
        }
        String format = DateExtensionUtils.format(calendar.getTime(), "yyyy-MM-dd");
        return DateExtensionUtils.parseDate(format);
    }

    @Override
    public WriteCellData<?> convertToExcelData(Date date, ExcelContentProperty excelContentProperty,
                                               GlobalConfiguration globalConfiguration) {
        java.util.Date date1 = new java.util.Date(date.getTime());
        return new WriteCellData(DateExtensionUtils.format(date1, "yyyy-MM-dd"));
    }
}
