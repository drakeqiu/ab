package com.jarvis.ab.persistent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by jarvis on 11/02/2017.
 */
public class AddressBookStorage {
    private static volatile AddressBookStorage INSTANCE;
    private Document document;
    public static AddressBookStorage getInstance() {
        if (INSTANCE == null) {
            synchronized (AddressBookStorage.class) {
                if (INSTANCE == null)
                    INSTANCE = new AddressBookStorage();
            }
        }
        return INSTANCE;
    }

    private AddressBookStorage(){};

    public void init() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
        }
    }

    public void parserXml(File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
        } catch (SAXException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
    }
}
