/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.helpers;

import generator.models.Report;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author mihail
 */
public class PropertiesParser extends DefaultHandler {
    private Report report;
    private String thisElement = "";
    private boolean pageProperties = false;
    private boolean columnProperties = false;
    private String currentColumnTitle;
    private Integer currentColumnWidth;

    
    public PropertiesParser(Report report) {
        this.report = report;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if(qName.equals("page")) {
            pageProperties = true;
        }
        if(qName.equals("column")) {
            columnProperties = true;
        }
        thisElement = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(qName.equals("page")) {
            pageProperties = false;
        }
        if(qName.equals("column")) {
            columnProperties = false;
            report.addHeaderColumn(currentColumnTitle, currentColumnWidth);
        }
        thisElement = "";
        
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(pageProperties) {
            if(thisElement.equals("width")) {
                this.report.setPageWidth(new Integer(new String(ch, start, length)));
            }
            if(thisElement.equals("height")) {
                this.report.setPageHeight(new Integer(new String(ch, start, length)));
            }
        }
        if(columnProperties) {
            if(thisElement.equals("title")) {
                currentColumnTitle = new String(ch, start, length);
            }
            if(thisElement.equals("width")) {
                currentColumnWidth = new Integer(new String(ch, start, length));
            }
        }
    }
    
    
}
