/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se150324_lab1.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import se150324_lab1.utils.Message;
import se150324_lab1.utils.Utils;

/**
 *
 * @author HuyRaw
 */
public class FoodManagement {

    private Scanner sc;
    private ArrayList<Food> foodList = new ArrayList();

    public FoodManagement() {
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public void createFood() throws ParseException {
        String _name, _id, _type, _place, _expiredDate;
        double _weight;
        int pos; // position in array 

        do {
            _id = Utils.getID(Message.MSG_INPUT_ID, Message.ERROR_WRONG_INPUT_ID, Message.REGEX_DOC_ID);
            pos = checkIDAvailable(_id);
            if (pos > 0) {
                System.out.println(Message.ERROR_DUPLICATE_ID);
            }
        } while (pos != -1);

        _name = Utils.getString(Message.MSG_INPUT_NAME, Message.ERROR_WRONG_INPUT_STR);
        _weight = Utils.getADouble(Message.MSG_INPUT_WEIGHT, Message.ERROR_WRONG_INPUT_DOUBLE);
        _type = Utils.getString(Message.MSG_INPUT_TYPE, Message.ERROR_WRONG_INPUT_STR);
        _place = Utils.getString(Message.MSG_INPUT_PLACE, Message.ERROR_WRONG_INPUT_STR);
        _expiredDate = Utils.getString(Message.MSG_INPUT_EXPIRED_DATE, Message.ERROR_WRONG_INPUT_STR);

        Food food = new Food(_id, _name, _weight, _type, _place, _expiredDate);
        foodList.add(food);
        System.out.println(Message.MSG_ADD_SUCCESSFULLY);
    }

    public void deleteFood() {
        String ID;

        ID = Utils.getID(Message.MSG_INPUT_FOOD_ID_TO_DELETE, Message.ERROR_WRONG_INPUT_ID, Message.REGEX_DOC_ID);
        System.out.println(Message.UI_DIV);

        if (delete(ID)) {
            System.out.println(Message.MSG_DELETE_FOOD_SUCCESSFULLY);
        } else {
            System.out.println(Message.ERROR_FOOD_NOT_FOUND);
        }
    }

    private boolean delete(String id) {
        return foodList.removeIf(food -> {
            boolean isMatch = food.getID().equalsIgnoreCase(id);
            return isMatch;
        });
    }

    public void updateFood() throws ParseException {
        String _id;
        _id = Utils.getID(Message.MSG_INPUT_FOOD_ID_TO_UPDATE, Message.ERROR_WRONG_INPUT_ID, Message.REGEX_DOC_ID);
        for (Food food : foodList) {
            if (food.getID().equalsIgnoreCase(_id)) {
                Food temp = new Food();
                //update name
                String strName = Utils.getString(Message.MSG_INPUT_NAME, Message.ERROR_WRONG_INPUT_STR);
                if (!strName.isEmpty()) {
                    temp.setName(strName);
                }
                //update weight
                String strWeight = Utils.getString(Message.MSG_INPUT_WEIGHT, Message.ERROR_WRONG_INPUT_DOUBLE);
                if (!strWeight.isEmpty()) {
                    double weight = Double.parseDouble(strWeight);
                    temp.setWeight(weight);
                }
                // update type
                String strType = Utils.getString(Message.MSG_INPUT_TYPE, Message.ERROR_WRONG_INPUT_STR);
                if (!strType.isEmpty()) {
                    temp.setType(strType);
                }
                //update place 
                String strPlace = Utils.getString(Message.MSG_INPUT_PLACE, Message.ERROR_WRONG_INPUT_STR);
                if (!strPlace.isEmpty()) {
                    temp.setPlace(strPlace);
                }
                //update expired date
                String strExpiredDate = Utils.getString(Message.MSG_INPUT_EXPIRED_DATE, Message.ERROR_WRONG_INPUT_STR);
                if (!strExpiredDate.isEmpty()) {
                    DateFormat dateFormat = new SimpleDateFormat(Message.DATE_FORMAT);
                    Date expiredDate = dateFormat.parse(strExpiredDate);
                    temp.setExpiredDate(expiredDate);
                }

                // MS to update successfully
                if (delete(_id)) {
                    foodList.add(temp);
                    System.out.println(Message.MSG_UPDATE_FOOD_SUCCESSFULLY);

                } else {
                    System.out.println(Message.MSG_UPDATE_FOOD_FAILED);
                }

            }

        }

    }

    //check ID exist
    private int checkIDAvailable(String ID) {
        if (foodList == null) {
            return -1;
        } else {
            for (Food food : foodList) {
                if (food.getID().equalsIgnoreCase(ID)) {
                    return 1;
                }
            }
        }
        return -1;
    }

    //search food by name
    public void listFoodByname() {
        boolean userChoice = false;
        sc = new Scanner(System.in);
        do {
            String strName = Utils.getString(Message.MSG_INPU_FOOD_NAME_TO_FIND, Message.ERROR_WRONG_INPUT_STR);
            for (Food food : foodList) {
                if (food.getName().equalsIgnoreCase(strName)) {
                    System.out.println(food.toString());
                }else{
                    break;
                }
            }
            System.out.println("Do you want to find another food ? (1 = yes/ 0 = No)");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    userChoice = true;
                    break;
                case 0:
                    userChoice = false;
                    break;
                default:
                    System.out.println("Error!!");
                    break;
            }

        } while (userChoice);

    }

    //comparison
    public void Comparison() {
        Collections.sort(foodList);
        System.out.println(Message.MSG_DATA_AFTER_SORT);
        display();

    }

    public void display() {
        System.out.println(String.format("|%10s|%20s|%7s|%20s|%20s|%20s|", "ID", "NAME", "WEIGHT",
                "TYPE", "PLACE", "EXPRIDED DATE"));
        for (Food food : foodList) {
            System.out.println(food.toString());
        }
    }

    //save to File
    public void saveToFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for (Food food : foodList) {
                    bw.newLine();
                    fw.write(food.toString());
                }
                System.out.println("Added succesfully!!");
                bw.close();
                fw.close();
            }
        } catch (Exception e) {
            System.out.println("Error!!");
        }

    }

}
