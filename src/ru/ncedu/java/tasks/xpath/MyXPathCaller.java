/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.xpath;

import java.io.IOException;
import java.util.ArrayList;
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

    private Element[] ConvertNodeListToElements(NodeList list) {
        Element[] res = new Element[list.getLength()];
        for (int i = 0; i < list.getLength(); i++) {
            res[i] = (Element) list.item(i);
        }
        return res;
    }

    @Override
    public Element[] getEmployees(Document src, String deptno, String docType) {
        Element[] elems = null;
        XPathExpression expr = null;
        try {
            XPathFactory xfactory = XPathFactory.newInstance();
            XPath xpath = xfactory.newXPath();
            switch (docType) {
                case "emp-hier":
                    expr = xpath.compile("//employee[@deptno=" + deptno + "]");
                    break;
                case "emp":
                    //TODO: как получать xpath для emp.xml?
                    expr = xpath.compile("emp/employee");
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                default:
                    throw new UnsupportedOperationException("Usage doctype: 'emp' or 'emp-hier'");
            }
            NodeList eval = (NodeList) expr.evaluate(src, XPathConstants.NODESET);
            elems = ConvertNodeListToElements(eval);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(MyXPathCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elems;
    }

    @Override
    public String getHighestPayed(Document src, String docType) {
        return getHighestPayed(src, null, docType);
    }

    @Override
    public String getHighestPayed(Document src, String deptno, String docType) {
        Double max = Double.MIN_VALUE;
        Element[] elems;
        XPathExpression expr = null;
        String res = "";
        String path = "//employee";
        if (deptno != null) {
            path += "[@deptno=" + deptno + "]";
        }
        path += "/sal";
        try {
            XPathFactory xfactory = XPathFactory.newInstance();
            XPath xpath = xfactory.newXPath();
            switch (docType) {
                case "emp-hier":
                    expr = xpath.compile(path);
                    break;
                case "emp":
                    //TODO: как получать xpath для emp.xml?
                    expr = xpath.compile("emp/employee");
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                default:
                    throw new UnsupportedOperationException("Usage doctype: 'emp' or 'emp-hier'");
            }
            NodeList eval = (NodeList) expr.evaluate(src, XPathConstants.NODESET);
            elems = ConvertNodeListToElements(eval);
            for (Element elem : elems) {
                Double sal = Double.parseDouble(elem.getTextContent());
                if (max < sal) {
                    max = sal;
                    Node findName = elem.getParentNode().getFirstChild();
                    while (!findName.getNodeName().equals("ename")) {
                        findName = findName.getNextSibling();
                    }
                    res = findName.getTextContent();
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(MyXPathCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public Element[] getTopManagement(Document src, String docType) {
        Element[] elems;
        ArrayList<Element> res = new ArrayList<>();
        XPathExpression expr = null;
        try {
            XPathFactory xfactory = XPathFactory.newInstance();
            XPath xpath = xfactory.newXPath();
            switch (docType) {
                case "emp-hier":
                    expr = xpath.compile("//employee");
                    break;
                case "emp":
                    //TODO: как получать xpath для emp.xml?
                    expr = xpath.compile("emp/employee");
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                default:
                    throw new UnsupportedOperationException("Usage doctype: 'emp' or 'emp-hier'");
            }
            NodeList eval = (NodeList) expr.evaluate(src, XPathConstants.NODESET);
            elems = ConvertNodeListToElements(eval);
            for (Element elem : elems) {
                if (!elem.getParentNode().getNodeName().equals("employee")) {
                    res.add(elem);
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(MyXPathCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res.toArray(new Element[res.size()]);
    }

    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        Element[] elems;
        ArrayList<Element> res = new ArrayList<>();
        XPathExpression expr = null;
        try {
            XPathFactory xfactory = XPathFactory.newInstance();
            XPath xpath = xfactory.newXPath();
            switch (docType) {
                case "emp-hier":
                    expr = xpath.compile("//employee");
                    break;
                case "emp":
                    //TODO: как получать xpath для emp.xml?
                    expr = xpath.compile("emp/employee");
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                default:
                    throw new UnsupportedOperationException("Usage doctype: 'emp' or 'emp-hier'");
            }
            NodeList eval = (NodeList) expr.evaluate(src, XPathConstants.NODESET);
            elems = ConvertNodeListToElements(eval);
            for (Element elem : elems) {
                if (elem.getElementsByTagName("employee").getLength() == 0) {
                    res.add(elem);
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(MyXPathCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res.toArray(new Element[res.size()]);
    }

    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        Element[] elems = null;
        XPathExpression expr = null;
        try {
            XPathFactory xfactory = XPathFactory.newInstance();
            XPath xpath = xfactory.newXPath();
            switch (docType) {
                case "emp-hier":
                    expr = xpath.compile("//employee[@empno=" + empno + "]");
                    break;
                case "emp":
                    //TODO: как получать xpath для emp.xml?
                    expr = xpath.compile("emp/employee");
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                default:
                    throw new UnsupportedOperationException("Usage doctype: 'emp' or 'emp-hier'");
            }
            Node eval = (Node) expr.evaluate(src, XPathConstants.NODE);
            Element manager = (Element) eval.getParentNode();
            elems = ConvertNodeListToElements(manager.getElementsByTagName("employee"));
        } catch (XPathExpressionException ex) {
            Logger.getLogger(MyXPathCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elems;
    }

}
