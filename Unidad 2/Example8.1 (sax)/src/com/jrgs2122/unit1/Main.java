package com.jrgs2122.unit1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class myXMLContactsHandler extends DefaultHandler {

    protected String currentTag;
    protected String tagContent;
    protected String cellPhone;


    // Tag opening found
    //
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        currentTag = qName;
        if ( currentTag.equals("contact") ) {
            System.out.println( "ID: " + attributes.getValue("id"));
        }
    }

    // Tag content, usually CDATA
    //
    public void characters(char ch[], int start, int length)
            throws SAXException {
        tagContent = new String(ch, start, length);
    }
    // Tag ending
    //
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ( !currentTag.isBlank() ) {
            //if ( !currentTag.equals( "contact" ) ) {
            //    System.out.println(" " + currentTag + ": " + tagContent);
            //    currentTag = "";
            //}
            if ( currentTag.equals("name"))
                System.out.print(tagContent);
            else
                if (currentTag.equals("surname"))
                    System.out.println(" "+tagContent);
                else
                    if (currentTag.equals("cell"))
                        cellPhone = tagContent;

        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            //SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse("contacts.xml", new myXMLContactsHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}