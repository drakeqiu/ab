package com.jarvis.ab.persistent;

import com.jarvis.ab.entity.Address;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarvis on 11/02/2017.
 */
public class AddressBookStoreServiceImpl implements AddressBookStoreService{
    private static volatile AddressBookStoreServiceImpl INSTANCE;
    private static final  String ADDRESSES = "addresses.xml";
    private Document document;
    public static AddressBookStoreServiceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (AddressBookStoreServiceImpl.class) {
                if (INSTANCE == null)
                    INSTANCE = new AddressBookStoreServiceImpl();
            }
        }
        return INSTANCE;
    }

    private AddressBookStoreServiceImpl(){}

    /**
     * store all addresses to the local file.
     */
    public void save(List<Address> addresses) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        PrintWriter printWriter;
        StreamResult result;
        DOMSource source;

        try {
            builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
            String fullAddressBookPath = this.getClass().getResource("/").getFile() + ADDRESSES;
            File file = new File(fullAddressBookPath);

            // save dom
            Element root = document.createElement("Addresses");
            for (Address address : addresses) {
                Element addressItemElement = document.createElement("AddressItem");
                Element nameElement = document.createElement("name");
                nameElement.setTextContent(address.getName());
                addressItemElement.appendChild(nameElement);
                Element mobileElement = document.createElement("mobile");
                mobileElement.setTextContent(address.getMobile());
                addressItemElement.appendChild(mobileElement);
                Element addressElement = document.createElement("address");
                addressElement.setTextContent(address.getAddress());
                addressItemElement.appendChild(addressElement);
                root.appendChild(addressItemElement);
            }
            this.document.appendChild(root);

            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            printWriter = new PrintWriter(new FileOutputStream(file));
            result = new StreamResult(printWriter);
            source = new DOMSource(document);
            transformer.transform(source,result);

        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
        } catch (TransformerException e) {
//            e.printStackTrace();
        }
    }

    public List<Address> load() {
        ArrayList<Address> addresses = new ArrayList<Address>();
        URL url = this.getClass().getResource("/");
        String fullPath = url.getFile() + ADDRESSES;
        File file = new File(fullPath);
        if (!file.exists()) {
            this.save(addresses);
            return addresses;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse(file);

            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            if (nodeList == null) return addresses;

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node addressNode = nodeList.item(i);
                if (addressNode.getNodeType() == Node.ELEMENT_NODE && addressNode.getNodeName().equals("AddressItem")) {
                    NodeList attrNodes = addressNode.getChildNodes();
                    if (attrNodes == null) continue;
                    Address address = new Address();
                    for (int j = 0; j < attrNodes.getLength(); ++ j) {
                        Node attr = attrNodes.item(j);
                        if (attr != null && attr.getNodeType() == Node.ELEMENT_NODE && attr.getNodeName().equals("name")) {
                            address.setName(attr.getNodeValue());
                        }
                        if (attr != null && attr.getNodeType() == Node.ELEMENT_NODE && attr.getNodeName().equals("mobile")) {
                            address.setMobile(attr.getNodeValue());
                        }
                        if (attr != null && attr.getNodeType() == Node.ELEMENT_NODE && attr.getNodeName().equals("address")) {
                            address.setAddress(attr.getNodeValue());
                        }
                    }
                    addresses.add(address);
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            return addresses;
        }

    }

    public static void main(String[] args) {
        AddressBookStoreServiceImpl storage = AddressBookStoreServiceImpl.getInstance();
        storage.load();
    }
}
