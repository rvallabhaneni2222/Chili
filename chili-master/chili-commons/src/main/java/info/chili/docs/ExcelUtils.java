/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.docs;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author ayalamanchili
 */
//TODO move to chili commons
public class ExcelUtils {

    public static String getCellStringOrNumericValue(Row record, int column) {
        if (getCellStringValue(record, column) != null) {
            return getCellStringValue(record, column);
        } else if (getCellNumericValue(record, column) != null) {
            return getCellNumericValue(record, column);
        }
        return null;
    }

    public static String getCellStringValue(Row record, int column) {
        if (record.getCell(column) != null && record.getCell(column).getCellType() == HSSFCell.CELL_TYPE_STRING && !record.getCell(column).getStringCellValue().isEmpty()) {
            return record.getCell(column).getStringCellValue();
        } else {
            return null;
        }
    }

    public static String getCellNumericValue(Row record, int column) {
        if (record.getCell(column) != null && record.getCell(column).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return record.getCell(column).toString();
        } else {
            return null;
        }
    }
}
