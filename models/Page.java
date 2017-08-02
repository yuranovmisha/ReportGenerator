/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.models;

import generator.helpers.TableHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mihail
 */
public class Page {
    private List<Row> rows = new ArrayList();
    private Integer[] cellWidths;
    private int height;
    private int width;
    public Page(Integer[] cellWidths, int width, int height) {
        this.cellWidths = cellWidths;
        this.width = width;
        this.height = height;
    }
    int createFromStrings(String[] header, List<String[]> strings) {
        Row tableHeader = new Row(header, this.cellWidths, this.width, true);
        rows.add(tableHeader);
        int currentHeight = tableHeader.getHeight();
        int rowsAdded = 0;
        for(String[] rowString: strings) {
            Row row = new Row(rowString, this.cellWidths, this.width, false);
            int rowHeight = row.getHeight();
            if(currentHeight + rowHeight > this.height) {
                return rowsAdded;
            }
            this.rows.add(row);
            rowsAdded++;
            currentHeight += rowHeight;
        }
        return rowsAdded;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Iterator<Row> iterator = this.rows.iterator(); iterator.hasNext();) {
            Row row = iterator.next();
            builder.append(row.toString());
            if(iterator.hasNext()) {
                builder.append(TableHelper.NEW_LINE);
            }
        }
        return builder.toString();
    }
    
}
