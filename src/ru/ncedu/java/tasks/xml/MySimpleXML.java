/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.xml;

import java.io.*;
import java.util.logging.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.transform.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 *
 * @author DimaZ
 */
public class MySimpleXML implements SimpleXML {

    @Override
    public String createXML(String tagName, String textNode) {
        StringWriter writer = null;
        try {
            writer = new StringWriter();
            Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = xmlDoc.createElement(tagName);
            root.appendChild(xmlDoc.createTextNode(textNode));
            xmlDoc.appendChild(root);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));

        } catch (TransformerException | ParserConfigurationException ex) {
            Logger.getLogger(MySimpleXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toString();
    }

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean bfname = false;
                boolean blname = false;
                boolean bnname = false;
                boolean bsalary = false;

                public void startElement(String uri, String localName, String qName,
                        Attributes attributes) throws SAXException {

                    System.out.println("Start Element :" + qName);

                    if (qName.equalsIgnoreCase("FIRSTNAME")) {
                        bfname = true;
                    }

                    if (qName.equalsIgnoreCase("LASTNAME")) {
                        blname = true;
                    }

                    if (qName.equalsIgnoreCase("NICKNAME")) {
                        bnname = true;
                    }

                    if (qName.equalsIgnoreCase("SALARY")) {
                        bsalary = true;
                    }

                }

                public void endElement(String uri, String localName,
                        String qName) throws SAXException {

                    System.out.println("End Element :" + qName);

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (bfname) {
                        System.out.println("First Name : " + new String(ch, start, length));
                        bfname = false;
                    }

                    if (blname) {
                        System.out.println("Last Name : " + new String(ch, start, length));
                        blname = false;
                    }

                    if (bnname) {
                        System.out.println("Nick Name : " + new String(ch, start, length));
                        bnname = false;
                    }

                    if (bsalary) {
                        System.out.println("Salary : " + new String(ch, start, length));
                        bsalary = false;
                    }

                }

            };

            saxParser.parse("c:\\file.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
