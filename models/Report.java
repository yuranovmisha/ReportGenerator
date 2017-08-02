/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.models;

import generator.helpers.TableHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mihail
 */
public class Report {
    private List<Page> pages = new ArrayList();
    private int pageWidth;
    private int pageHeight;
    private List<Integer> columnsWidths;
    private List<String> header = new ArrayList();
    public Report() {
        this.columnsWidths = new ArrayList();
    }
    public Report(int pageWidth, int pageHeight, Integer[] columnsWidths) {
        this.pageWidth = pageWidth;
        this.pageHeight = pageHeight;
        this.columnsWidths = Arrays.asList(columnsWidths);
    }
    public void createPages(List<String[]> rows) {
        List<String[]> currentRows = new ArrayList(rows);
        while(currentRows.size() > 0) {
            Page page = new Page(this.columnsWidths.toArray(new Integer[0]), 
                    this.pageWidth, this.pageHeight);
            int rowsAdded = page.createFromStrings(header.toArray(new String[0]),
                    currentRows);
            currentRows.subList(0, rowsAdded).clear();
            this.pages.add(page);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Iterator<Page> iterator = this.pages.iterator(); iterator.hasNext();) {
            Page page = iterator.next();
            builder.append(page.toString());
            if(iterator.hasNext()) {
                builder.append(TableHelper.NEW_LINE);
                builder.append(TableHelper.PAGES_SEPARATOR);
                builder.append(TableHelper.NEW_LINE);
            }
        }
        return builder.toString();
    }

    public void setPageWidth(int pageWidth) {
        this.pageWidth = pageWidth;
    }

    public void setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
    }
    
    public void addHeaderColumn(String text, int width) {
        this.header.add(text);
        this.columnsWidths.add(width);
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }
    
    
}
