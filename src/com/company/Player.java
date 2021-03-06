package com.company;

import java.util.*;

public class Player {
    String Name;
    int Money = 2500;
    List<Animal> Animals = new ArrayList<Animal>();
    public int[] Food = new int[3];
    public boolean Bankrupt = false;

    public void DisplayInfo() {
        //Displays all Animals the player currently own
        System.out.println("Current Animals:");
        for (int i = 0; i < Animals.size(); i++) {
            System.out.println(Animals.get(i).myName + ":"); //Animal Name
            System.out.println("    Health: " + Animals.get(i).myHealth + "%"); //Animal Health
            System.out.println("    Type: " + Animals.get(i).getClass().getSimpleName());//Animal Type
            System.out.println("    Gender: " + Animals.get(i).myGender.toString());//Animal Gender
            System.out.println("    Age: " + Animals.get(i).myAge + " Turns/" + Animals.get(i).myMaxAge + " Turns"); //Animal Age
            System.out.println("    Estimated Value: " + Animals.get(i).GetValue() + " Schmeckles"); //Animal Estimated Value (Based on age)
        }
        //If the player has no animals
        if (Animals.size() == 0)
            System.out.println("    None");

        DisplayFood();

        //Print how much money the player has
        System.out.println("Money: " + Money + " Schmeckles");

        System.out.println();
    }

    public void DisplayAnimalsIndexed() {
        //Display all animals with an index
        for (int i = 0; i < Animals.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + Animals.get(i).myName + ":"); //Animal Name
            System.out.println("    Health: " + Animals.get(i).myHealth + "%"); //Animal Health
            System.out.println("    Type: " + Animals.get(i).getClass().getSimpleName());//Animal Type
            System.out.println("    Gender: " + Animals.get(i).myGender.toString());//Animal Gender
            System.out.println("    Age: " + Animals.get(i).myAge + " Turns/" + Animals.get(i).myMaxAge + " Turns"); //Animal Age
            System.out.println("    Estimated Value: " + Animals.get(i).GetValue() + " Schmeckles"); //Animal Estimated Value (Based on age)
        }
    }
    public void DisplayFood(){
        //Displays owned food
        System.out.println("Food:");
        System.out.println("    Silage: " + Food[0] + " Kg");
        System.out.println("    Seeds: " + Food[1] + " Kg");
        System.out.println("    Carrots: " + Food[2] + " Kg");
    }
}
