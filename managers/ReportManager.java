/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.managers;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import generator.helpers.PropertiesParser;
import generator.helpers.TableHelper;
import generator.models.Report;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author mihail
 */
public class ReportManager {
    public static void makeReport(String tsvFileName, String xmlFileName,
            String outputFileName) {
        try {
            TsvParserSettings settings = new TsvParserSettings();
            settings.getFormat().setLineSeparator(String.valueOf(TableHelper.NEW_LINE));
            TsvParser parser = new TsvParser(settings);
            
            List<String[]> allRows = parser.parseAll(getReader(tsvFileName));
            
            Report report = new Report();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser xmlParser = factory.newSAXParser();
            PropertiesParser saxp = new PropertiesParser(report);
            xmlParser.parse(new File(xmlFileName), saxp);
            
            report.createPages(allRows);
            System.out.print(report);
            PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
            writer.print(report);
             writer.close();        
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Reader getReader(String relativePath){
        try {
            File file = new File(relativePath);
            
            InputStream is = null;
            try {
                is = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            return new InputStreamReader(is, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unable to read input", e);
        }
    }

}
