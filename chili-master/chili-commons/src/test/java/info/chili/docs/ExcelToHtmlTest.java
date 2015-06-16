/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.docs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author ayalamanchili
 */
public class ExcelToHtmlTest {

//    @Test
    public void testExcelToHtml() {
        try {
            System.out.println(new ExcelToHtml(new FileInputStream(new File("/home/ayalamanchili/Downloads/Contracts Team Activity Report - Week of 04-27-15.xls"))).getHTML());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
