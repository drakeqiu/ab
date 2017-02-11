package com.jarvis.ab.persistent;

import com.jarvis.ab.entity.Address;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarvis on 11/02/2017.
 */
public class AddressBookStorage {
    private static volatile AddressBookStorage INSTANCE;
    private static final  String ADDRESSFILE = "addresses.xml";
    private List<Address> addresses;
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

    public void load() {
        if (addresses == null ) {
            addresses = new ArrayList<Address>();
            String path = this.getClass().getResource("/").getPath();
            String fullAddressBookPath = path + ADDRESSFILE;
            File file = new File(fullAddressBookPath);
            if (!file.exists()) {
                createXml(file);
            } else {
                parserXml(file);
            }
        }
    }

    public void init() throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
    }

    public void createXml(File file) {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        PrintWriter printWriter = null;
        StreamResult result = null;
        DOMSource source = null;
        try {

            this.init();
            Element root = document.createElement("addresses");
            this.document.appendChild(root);

            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            printWriter = new PrintWriter(new FileOutputStream(file));
            result = new StreamResult(printWriter);
            source = new DOMSource(document);
            transformer.transform(source,result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        AddressBookStorage storage = AddressBookStorage.getInstance();
        storage.load();
    }
}
