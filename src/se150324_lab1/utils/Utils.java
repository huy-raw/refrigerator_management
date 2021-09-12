/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150324_lab1.utils;

import java.util.Scanner;

/**
 *
 * @author HuyRaw
 */
public class Utils {
    private static Scanner sc;
    
     //get ID with form 
    public static String getID(String inputMsg, String errorMsg, String format) {
        sc = new Scanner(System.in);
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);

            id = sc.nextLine().trim().toLowerCase();

            match = id.matches(format);
            if (match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }
    
    //get integer
     //hàm nhập 1 số nguyên
    public static double getADouble(String inputMsg, String errorMsg) {
        sc = new Scanner(System.in);
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < 0) throw new IncorrectNumberException(Message.MSG_MUST_GREATER_THAN_ZERO);
                return n;
            } catch (NumberFormatException | IncorrectNumberException e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    
    
    //get string
     public static String getString(String inputMsg, String errorMsg) {
        sc = new Scanner(System.in);
        String string;
        while (true) {
            System.out.print(inputMsg);
            string = sc.nextLine().trim();
            if (string.length() == 0) {
                System.out.println(errorMsg);
            } else {
                return string;
            }
        }
    }
     
     
      
     
    
    //Menu
     public static void printManageMenu(){
         System.out.println(Message.UI_DIV);
         System.out.println("--Welcome to the Refrigerator Management System--");
         System.out.println(Message.UI_DIV);
         System.out.println("1 - Add a new food");
         System.out.println("2 - Search a food by name");
         System.out.println("3 - Remove the food by ID");
         System.out.println("4 - Print the food list in the descending order of expired date");
         System.out.println("5 - Save to file");
         System.out.println("6 - Exit");
         
         
     }
     
     
     
     
     
       
}

class IncorrectNumberException extends Exception { 
    public IncorrectNumberException(String errorMessage) {
        super(errorMessage);
    }
}


