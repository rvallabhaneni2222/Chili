/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.reporting;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Bhavana.Atluri
 */
public class ReflectiveReportBuilder extends ar.com.fdvs.dj.domain.builders.ReflectiveReportBuilder{
    
    Style currencyStyle;
    Style numberStyle;
    
    public ReflectiveReportBuilder(Collection _data) {
        super(_data);
        currencyStyle = new Style("currencyStyle");
        numberStyle = new Style("numberStyle");
    }
    
    public ReflectiveReportBuilder(Collection _data, String[] _propertiesNames) {
        super(_data,_propertiesNames);
        currencyStyle = new Style("currencyStyle");
        numberStyle = new Style("numberStyle");
    }
    
    @Override
    protected void guessStyle(String className, AbstractColumn column) throws ClassNotFoundException {

		Class clazz = Class.forName(className);
		if (BigDecimal.class.isAssignableFrom(clazz) || Float.class.isAssignableFrom(clazz) || Double.class.isAssignableFrom(clazz)) {
			if (column.getPattern() == null)
				column.setPattern("$ #.00");
			column.setStyle(currencyStyle);
		}

		if (Integer.class.isAssignableFrom(clazz) || Long.class.isAssignableFrom(clazz)) {
			column.setStyle(numberStyle);
		}

		if (Date.class.isAssignableFrom(clazz)) {
			if (column.getPattern() == null)
				column.setPattern("MM/dd/yyyy");
		}

		if (Timestamp.class.isAssignableFrom(clazz)) {
			if (column.getPattern() == null)
				column.setPattern("MM/dd/yy hh:mm:ss");
		}
	}

}
