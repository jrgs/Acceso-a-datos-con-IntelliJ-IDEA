package com.jrgs2122.unit1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

public class XmlDomReadExample {

    public void  SequentialReading( Document document ) {
        DocumentTraversal traversal = (DocumentTraversal) document;

        NodeIterator iterator = traversal.createNodeIterator(
                document.getDocumentElement(), NodeFilter.SHOW_TEXT, null, true);

        for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {

            String text = n.getTextContent().trim();

            if (!text.isEmpty()) {
                System.out.println(text);
            }
        }
    }

    public static void RandomReading( Document doc ) {
        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("contact");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");

                Node node1 = elem.getElementsByTagName("name").item(0);
                String fname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("surname").item(0);
                String lname = node2.getTextContent();

                System.out.printf("User id: %s%n", uid);
                System.out.printf("First name: %s%n", fname);
                System.out.printf("Last name: %s%n", lname);
             }
        }
    }

    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();
        Document document = loader.parse("contacts.xml");

        RandomReading( document );
    }
}