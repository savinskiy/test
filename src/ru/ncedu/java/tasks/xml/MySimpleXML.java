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

public class MySimpleXML implements SimpleXML {
    private String root = null;

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

                @Override
                public void startElement(String uri, String localName, String qName,
                        Attributes attributes) throws SAXException {
                    if (root == null) {
                        root = qName;
                    }
                }
            };

            saxParser.parse(xmlStream, handler);

        } catch (IOException | ParserConfigurationException ex) {
            Logger.getLogger(MySimpleXML.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return root;
    }
}