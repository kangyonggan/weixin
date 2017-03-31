package com.kangyonggan.app.weixin.biz.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URL;

/**
 * xml工具类
 *
 * @author kangyonggan
 * @since 2017/3/31
 */
public class XmlUtil {

    private static final SAXReader reader = new SAXReader();

    public static Document read(String filename) throws DocumentException {
        return reader.read(new File(filename));
    }

    public static Document read(File file) throws DocumentException {
        return reader.read(file);
    }

    public static Document read(URL url) throws DocumentException {
        return reader.read(url);
    }

    public static Document parseText(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }

}
