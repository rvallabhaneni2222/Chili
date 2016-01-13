/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.docs;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Convert a POJO into a simple 2 column HTML table.
 */
public class MakeHTML {

    private static final class HTMLStyle extends ToStringStyle {

        public HTMLStyle() {
            setFieldSeparator("</td></tr>" + SystemUtils.LINE_SEPARATOR + "<tr><td style=\"border: 1px solid rgb(204, 204, 204); padding: 5px 10px; \">");

            setContentStart("<table>" + SystemUtils.LINE_SEPARATOR
                    + "<thead><tr><th style=\"border: 1px solid rgb(204, 204, 204); padding: 5px 10px; background: rgb(238, 238, 238);\">Field</th><th style=\"border: 1px solid rgb(204, 204, 204); padding: 5px 10px; background: rgb(238, 238, 238);\">Data</th></tr></thead>"
                    + "<tbody><tr><td style=\"border: 1px solid rgb(204, 204, 204); padding: 5px 10px; \">");

            setFieldNameValueSeparator("</td><td style=\"border: 1px solid rgb(204, 204, 204); padding: 5px 10px; \">");

            setContentEnd("</td></tr>" + SystemUtils.LINE_SEPARATOR + "</tbody></table>");

            setArrayContentDetail(true);
            setUseShortClassName(true);
            setUseClassName(false);
            setUseIdentityHashCode(false);
        }

        @Override
        public void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value.getClass().getName().startsWith("java.lang")) {
                super.appendDetail(buffer, fieldName, value);
            } else if (value instanceof Date) {
                value = new SimpleDateFormat("MM/dd/yyyy").format(value);
                super.appendDetail(buffer, fieldName, value);
            } else if (value instanceof BigDecimal) {
                BigDecimal val = (BigDecimal) value;
                super.appendDetail(buffer, fieldName, val.doubleValue());
            } else if (value.getClass().isEnum()) {
                value = String.valueOf(value);
                super.appendDetail(buffer, fieldName, value);
            } else {
                buffer.append(ReflectionToStringBuilder.toString(value, this));
            }
        }
    }

    static public String makeHTML(Object object) {
        return ReflectionToStringBuilder.toString(object, new HTMLStyle());
    }
}
