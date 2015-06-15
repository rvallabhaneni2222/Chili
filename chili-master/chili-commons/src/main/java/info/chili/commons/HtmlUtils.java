/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.commons;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.tidy.Tidy;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Locale;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.outerj.daisy.diff.HtmlCleaner;
import org.outerj.daisy.diff.html.HTMLDiffer;
import org.outerj.daisy.diff.html.HtmlSaxDiffOutput;
import org.outerj.daisy.diff.html.TextNodeComparator;
import org.outerj.daisy.diff.html.dom.DomTreeBuilder;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

/**
 *
 * @author ayalamanchili
 */
public class HtmlUtils {

    public static String cleanData(String data) {
        if (data == null) {
            return "";
        }
        Tidy tidy = new Tidy();
        tidy.setPrintBodyOnly(true);
        tidy.setXmlOut(true);
        ByteArrayInputStream inputStream;
        try {
            inputStream = new ByteArrayInputStream(data.getBytes("UTF-8"));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            tidy.parseDOM(inputStream, outputStream);
            return outputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String htmlToText(String html) {
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }

    public static String getDiffForHTMLInput(String contentOld, String contentNew)
            throws Exception {
        contentOld = contentOld == null ? "" : contentOld;
        contentNew = contentNew == null ? "" : contentNew;
        StringWriter writer = new StringWriter(contentNew.length()
                + contentOld.length());
        //List styleList = new ArrayList();
        //styleList.add("/static/js/difftag/css/difftag.css");

        SAXTransformerFactory tf = (SAXTransformerFactory) TransformerFactory
                .newInstance();
        TransformerHandler result = tf.newTransformerHandler();
        result.setResult(new StreamResult(writer));

        ContentHandler postProcess = result;
        Locale locale = Locale.getDefault();
        String prefix = "diff";
        HtmlCleaner cleaner = new HtmlCleaner();
        InputSource oldSource = new InputSource(new ByteArrayInputStream(
                contentOld.getBytes("UTF-8")));
        InputSource newSource = new InputSource(new ByteArrayInputStream(
                contentNew.getBytes("UTF-8")));
        DomTreeBuilder oldHandler = new DomTreeBuilder();
        cleaner.cleanAndParse(oldSource, oldHandler);
        TextNodeComparator leftComparator = new TextNodeComparator(oldHandler,
                locale);
        DomTreeBuilder newHandler = new DomTreeBuilder();
        cleaner.cleanAndParse(newSource, newHandler);
        TextNodeComparator rightComparator = new TextNodeComparator(newHandler,
                locale);
        postProcess.startDocument();

        //postProcess.startElement("", "diffreport", "diffreport", new AttributesImpl());
        // attachStyleSheets(styleList, postProcess);
        //postProcess.startElement("", "diff", "diff", new AttributesImpl());
        HtmlSaxDiffOutput output = new HtmlSaxDiffOutput(postProcess, prefix);
        HTMLDiffer differ = new HTMLDiffer(output);
        differ.diff(leftComparator, rightComparator);
        //postProcess.endElement("", "diff", "diff");
        //postProcess.endElement("", "diffreport", "diffreport");
        postProcess.endDocument();
        return writer.getBuffer().toString();
    }
}