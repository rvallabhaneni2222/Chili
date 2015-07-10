/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.docs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author ayalamanchili
 */
public class DocxToHtml {

    public static void main(String... args) throws Exception {
// 1) Load DOCX into XWPFDocument
        InputStream in = new FileInputStream(new File("/home/ayalamanchili/Downloads/Status Report_Solution Team_WE 10 May 15.docx"));
        XWPFDocument document = new XWPFDocument(in);

// 2) Prepare XHTML options (here we set the IURIResolver to load images from a "word/media" folder)
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("word/media")));

// 3) Convert XWPFDocument to XHTML
        OutputStream out = new FileOutputStream(new File("/home/ayalamanchili/Downloads/HelloWord.htm"));
        XHTMLConverter.getInstance().convert(document, out, options);
    }

    public static String convert(final InputStream in) throws Exception {
        XWPFDocument document = new XWPFDocument(in);
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("word/media")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, out, options);
        return new String(out.toByteArray(), "UTF-8");
    }
}
