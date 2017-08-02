/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.models;

import generator.helpers.StringHelper;
import generator.helpers.TableHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author mihail
 */
public class Cell {
    private List<String> text;
    private int width;
    private int height = 0;
    private final int LEFT_PADDING = 1;
    private final int RIGHT_PADDING = 1;
    private boolean firstColumn;
    private List<List<String>> arrayPresentation;
    public Cell(String text, int width, boolean firstColumn) {
        this.width = width;
        this.firstColumn = firstColumn;
        this.text = StringHelper.divideText(text, width);
        this.arrayPresentation = toArray();
    }


    private List<List<String>> toArray() {
        char[] leftPadding = new char[LEFT_PADDING];
         for (int i = 0; i < LEFT_PADDING; i++) {
            leftPadding[i] = TableHelper.PADDING_SEPARATOR;
        }
        List<List<String>> builder = new ArrayList();
        for (Iterator<String> iterator = this.text.iterator(); iterator.hasNext();) {
            List<String> line = new ArrayList();
            String word = iterator.next();
            if (this.firstColumn) {
                line.add(String.valueOf(TableHelper.VERTICAL_SEPARATOR));
            }
            line.add(String.valueOf(leftPadding));
            line.add(word);
            int rightPaddingLength = this.width - word.length() + RIGHT_PADDING;
            char[] rightPadding = new char[rightPaddingLength];
            for (int i = 0; i < rightPaddingLength; i++) {
                rightPadding[i] = TableHelper.PADDING_SEPARATOR;
            }
            line.add(String.valueOf(rightPadding));
            line.add(String.valueOf(TableHelper.VERTICAL_SEPARATOR));
            builder.add(line);
          }
        return builder;
    }
    
    
  
    int getHeight() {
        return this.text.size();
    }

    int getWidth() {
        return width + this.LEFT_PADDING + this.RIGHT_PADDING;
    }

    boolean isFirstColumn() {
        return firstColumn;
    }

    public List<List<String>> getArrayPresentation() {
        return arrayPresentation;
    }
    
    
}
