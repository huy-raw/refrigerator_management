/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150324_lab1.data;

import java.text.ParseException;
import java.util.Scanner;
import se150324_lab1.utils.Message;
import se150324_lab1.utils.Utils;

/**
 *
 * @author HuyRaw
 */
public class FoodManagementSystem {

    private static Scanner sc;
    private static int userChoice;
    private static FoodManagement food;

    public static void main(String[] args) throws ParseException {
        boolean isExit = false;
        sc = new Scanner(System.in);
        food = new FoodManagement();

        do {
            try {
                isExit = appMenu();
            } catch (ParseException ex) {
                System.out.println(Message.ERROR_WRONG_INPUT_INT);
            }
        } while (!isExit);

    }

    
    
    
    //menu
    public static boolean appMenu() throws ParseException {

        String fileName = "ListFood.txt";
        Utils.printManageMenu();

        try {
            userChoice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println(Message.ERROR_WRONG_INPUT_INT);
            return false;
        }
        switch (userChoice) {
            case 1:
                food.createFood();
                break;
            case 2:
                food.listFoodByname();
                break;
            case 3:
                food.deleteFood();
                break;
            case 4:
                food.Comparison();
                break;
            case 5:
                food.saveToFile(fileName);
                break;
            case 6:
                return true;

        }

        return false;   
    }

}
