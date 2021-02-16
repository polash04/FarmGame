package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int Money = 1000;
    List<Animal> Animals = new ArrayList<Animal>();

    public void DisplayInfo(){
        //Print how much money the player has
        System.out.println("Money: " + Money + " Schmeckles");

        //Displays all Animals the player currently own
        System.out.println("Current Animals:");
        for (int i = 0; i < Animals.size(); i++) {
            System.out.println(Animals.get(i).myName + ":"); //Animal Name
            System.out.println("    Health: " + Animals.get(i).myHealth + "%"); //Animal Health
            System.out.println("    Type: " + Animals.get(i).getClass().getSimpleName());//Animal Type
            System.out.println("    Gender: " + Animals.get(i).myGender.toString());//Animal Gender
            System.out.println("    Age: " + Animals.get(i).myAge + " Turns/" + Animals.get(i).myMaxAge + " Turns"); //Animal Age
            System.out.println("    Estimated Value: " + Animals.get(i).GetValue()); //Animal Estimated Value (Based on age)
        }
        //If the player has no animals
        if(Animals.size() == 0)
            System.out.println("    None");

        System.out.println();
    }
}
