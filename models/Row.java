/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.models;

import generator.helpers.TableHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mihail
 */
public class Row {
    private List<Cell> cells = new ArrayList();
    private boolean header;
    private int rowWidth;
    public Row(String[] cellsText, Integer[] cellWidths, int width, boolean header) {
        this.header = header;
        createCells(cellsText, cellWidths);
        this.rowWidth = width;
    }
    private int calculateWidth(int[] cellWidths) {
        int width = 0;
        for(int cellWidth: cellWidths) {
            width += cellWidth;
        }
        return width;
    }
    private void createCells(String[] cellsText, Integer[] cellWidths) {
        for(int i = 0; i < cellsText.length; i++) {
            boolean firstCell = i == 0;
            cells.add(new Cell(cellsText[i], cellWidths[i], firstCell));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(this.header) {
            for(int i = 0; i < this.rowWidth; i++) {
                builder.append(TableHelper.HORIZONTAL_SEPARATOR);
            }
            builder.append(TableHelper.NEW_LINE);
        }
        int height = this.getHeight() - (header ? 2 : 1);
        for(int line = 0; line < height; line++) {
            for(Cell cell: cells) {
                if(cell.getHeight() > line) {
                    for(String item: cell.getArrayPresentation().get(line)) {
                        builder.append(item);
                    }
                } else {
                    if(cell.isFirstColumn()) {
                        builder.append(TableHelper.VERTICAL_SEPARATOR);
                    }
                    for(int i = 0; i < cell.getWidth(); i++) {
                        builder.append(TableHelper.PADDING_SEPARATOR);
                    }
                    builder.append(TableHelper.VERTICAL_SEPARATOR);
                }
            }
            builder.append(TableHelper.NEW_LINE);
        }
        for(int i = 0; i < this.rowWidth; i++) {
            builder.append(TableHelper.HORIZONTAL_SEPARATOR);
        }
        return builder.toString();
    }
    int getHeight() {
        int maxHeight = 0;
        for(Cell cell: cells) {
            if(cell.getHeight() > maxHeight) {
                maxHeight = cell.getHeight();
            }
        }
        if(this.header) {
            maxHeight++;
        }
        return maxHeight + 1;
    }
}
