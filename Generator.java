/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import generator.managers.ReportManager;
import generator.models.Cell;
import java.util.Scanner;

/**
 *
 * @author mihail
 */
public class Generator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("Wrong number of arguments");
            return;
        }
        ReportManager.makeReport(args[1], args[0], args[2]);
    }
    
}
