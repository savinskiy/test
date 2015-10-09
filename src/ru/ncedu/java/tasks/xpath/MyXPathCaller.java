/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.xpath;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import javax.xml.xpath.*;

/**
 *
 * @author DimaZ
 */
public class MyXPathCaller implements XPathCaller {

    @Override
    public Element[] getEmployees(Document src, String deptno, String docType) {
        Element[] res = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = factory.newDocumentBuilder();
            src = builder.parse("src/ru/ncedu/java/tasks/xpath/" + docType + ".xml");
            XPathFactory xfactory = XPathFactory.newInstance();
            XPath xpath = xfactory.newXPath();
            XPathExpression expr = xpath.compile("//employee[deptno='" + deptno + "']");
            NodeList eval = (NodeList) expr.evaluate(src, XPathConstants.NODESET);
            res = new Element[eval.getLength()];
            for (int i = 0; i < eval.getLength(); i++) {
                res[i] = (Element) eval.item(i);
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(MyXPathCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public String getHighestPayed(Document src, String docType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHighestPayed(Document src, String deptno, String docType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element[] getTopManagement(Document src, String docType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
