/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mihail
 */
public class StringHelper {
    public static List<String> divideText(String text, int width) {
       List<String> stringList = new ArrayList();
       if (text.length() <= width) {
            stringList.add(text);
            return stringList;
        }
        int symbolPointer = 0;
        char[] charText = text.toCharArray();
        int charCounter = 0;
        int charInStringCounter = 0;
        int dividerIndex = -1;
        while (charCounter < charText.length) {
            int charOffset = 1;
            if (!Character.isLetterOrDigit(charText[charCounter])) {
                dividerIndex = charCounter;
            }
            if (charInStringCounter >= width) {
                int endWordIndex;
                if (dividerIndex > 0) {
                    if (!Character.isWhitespace(charText[dividerIndex])) {
                        endWordIndex = dividerIndex + 1;
                    } else {
                        endWordIndex = dividerIndex;
                        charOffset++;
                    }
                    charCounter = endWordIndex;
                } else {
                    endWordIndex = charCounter;
                }
                charInStringCounter = 0;
                int offset = charOffset - 1;
                if (endWordIndex - symbolPointer > width) {
                    endWordIndex--;
                    charInStringCounter++;
                    offset--;
                }
                stringList.add(text.substring(symbolPointer, endWordIndex));
                symbolPointer = charCounter + offset;
                dividerIndex = -1;
            }
            charCounter += charOffset;
            charInStringCounter++;
        }
        if (charInStringCounter > 0) {
            stringList.add(text.substring(symbolPointer));
        }
        return stringList;
    }    
}
