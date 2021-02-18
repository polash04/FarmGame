package com.company;

import jdk.jshell.execution.Util;

import java.util.Scanner;

public class Store {

    //Checks if the player can afford a price
    static boolean CanAfford(Player aPlayer, int aCost) {
        if (aPlayer.Money < aCost) {
            System.out.println("You cannot afford that!");
            return false;
        } else
            return true;

    }

    public static void BuyAnimal(Player aPlayer) {
        //loop until "Done" is chosen
        while (true) {
            Animal tempAnimal = null;

            //Display Info
            aPlayer.DisplayInfo();

            //Give the player the different options of animals
            switch (Utility.GiveOptions("Chicken(" + Chicken.Cost + " Schmeckles)",
                    "Cow(" + Cow.Cost + " Schmeckles)",
                    "Pig(" + Pig.Cost + " Schmeckles)",
                    "Sheep(" + Sheep.Cost + " Schmeckles)",
                    "Horse(" + Horse.Cost + " Schmeckles)",
                    "Done")) {
                //All cases do the same with only the animal being different

                //Checks if player can afford the animal
                //if they can afford it create a new instance of the animal and remove the correct amount of money
                case 0:
                    if (!CanAfford(aPlayer, Chicken.Cost))
                        break;

                    tempAnimal = new Chicken();
                    System.out.println("-" + Chicken.Cost + " Schmeckles");
                    aPlayer.Money -= Chicken.Cost;
                    break;
                case 1:
                    if (!CanAfford(aPlayer, Cow.Cost))
                        break;

                    tempAnimal = new Cow();
                    System.out.println("-" + Cow.Cost + " Schmeckles");
                    aPlayer.Money -= Cow.Cost;
                    break;
                case 2:
                    if (!CanAfford(aPlayer, Pig.Cost))
                        break;

                    tempAnimal = new Pig();
                    System.out.println("-" + Pig.Cost + " Schmeckles");
                    aPlayer.Money -= Pig.Cost;
                    break;
                case 3:
                    if (!CanAfford(aPlayer, Sheep.Cost))
                        break;

                    tempAnimal = new Sheep();
                    System.out.println("-" + Sheep.Cost + " Schmeckles");
                    aPlayer.Money -= Sheep.Cost;
                    break;
                case 4:
                    if (!CanAfford(aPlayer, Horse.Cost))
                        break;

                    tempAnimal = new Horse();
                    System.out.println("-" + Horse.Cost + " Schmeckles");
                    aPlayer.Money -= Horse.Cost;
                    break;

                case 5:
                    System.out.println("Shopkeep: Pleasure doing business with you!\n");
                    return;
            }
            //Only add the animal if it isn't null, animal will be null when the player cannot afford the animal
            if (tempAnimal != null) {
                aPlayer.Animals.add(tempAnimal);
            }
        }
    }


    public static void BuyFood(Player aPlayer) {
        while (true) {
            //Display info
            aPlayer.DisplayInfo();
            //Initialize variable
            FoodType tempFoodType = FoodType.Silage;

            //Let the player choose a food type
            switch (Utility.GiveOptions("Silage (" + Silage.Cost + " Schmeckles/Kg)",
                    "Seeds(" + Seeds.Cost + " Schmeckles/Kg)",
                    "Carrots(" + Carrots.Cost + " Schmeckles/Kg)",
                    "Done")) {
                case 0:
                    tempFoodType = FoodType.Silage;
                    break;
                case 1:
                    tempFoodType = FoodType.Seeds;
                    break;
                case 2:
                    tempFoodType = FoodType.Carrots;
                    break;
                case 3:
                    System.out.println("Shopkeep: Pleasure doing business with you!\n");
                    return;
            }
            //Get how many kg the player wants to buy
            System.out.println("How many Kg of " + tempFoodType.toString() + " do you want to buy?");
            int tempAnswer = Utility.GetInt();

            //Determine cost based on FoodType
            int tempCost = 0;
            if (tempFoodType == FoodType.Silage)
                tempCost = Silage.Cost;
            else if (tempFoodType == FoodType.Seeds)
                tempCost = Seeds.Cost;
            else if (tempFoodType == FoodType.Carrots)
                tempCost = Carrots.Cost;

            //Check if the player can afford the food
            if (CanAfford(aPlayer, tempAnswer * tempCost)) {
                //Increase the food amount appropriately
                aPlayer.Food[tempFoodType.getValue()] += tempAnswer;

                //Print and remove the money
                System.out.println("-" + tempAnswer * tempCost + " Schmeckles");
                aPlayer.Money -= tempAnswer * tempCost;
            }
        }

    }

    public static void SellAnimal(Player aPlayer) {
        System.out.println("Which animal do you want to sell?");
        //Loop forever
        while (true) {

            //print how much money the player has
            System.out.println("Money: " + aPlayer.Money + " Schmeckles");

            //Display all animals with an index
            for (int i = 0; i < aPlayer.Animals.size(); i++) {
                System.out.println("[" + (i + 1) + "]" + aPlayer.Animals.get(i).myName + ":"); //Animal Name
                System.out.println("    Health: " + aPlayer.Animals.get(i).myHealth + "%"); //Animal Health
                System.out.println("    Type: " + aPlayer.Animals.get(i).getClass().getSimpleName());//Animal Type
                System.out.println("    Gender: " + aPlayer.Animals.get(i).myGender.toString());//Animal Gender
                System.out.println("    Age: " + aPlayer.Animals.get(i).myAge + " Turns/" + aPlayer.Animals.get(i).myMaxAge + " Turns"); //Animal Age
                System.out.println("    Estimated Value: " + aPlayer.Animals.get(i).GetValue() + " Schmeckles"); //Animal Estimated Value (Based on age)
            }

            //If the player has no animals
            if(aPlayer.Animals.size() == 0){
                System.out.println("You have no animals to sell...");
            }

            //Print the done option with the appropriate number
            System.out.println("[" + (aPlayer.Animals.size() + 1) + "]" + "Done");
            //Get an answer between the bounds
            int tempAnswer = Utility.GetIntBetween(1, aPlayer.Animals.size() + 1);

            //If "Done" was chosen
            if (tempAnswer == aPlayer.Animals.size() + 1) {
                return;
            }

            //Make sure the player didn't make a wrong input by accident
            System.out.println("Are you sure you want to sell " + aPlayer.Animals.get(tempAnswer- 1).myName);
            //If answer is yes
            if (Utility.GiveOptions("Yes", "No") == 0) {
                //Give the player and display how much
                System.out.println("+" + aPlayer.Animals.get(tempAnswer - 1).GetValue() + " Schmeckles");
                aPlayer.Money += aPlayer.Animals.get(tempAnswer - 1).GetValue();

                //Remove the animal from the player and make sure
                if (aPlayer.Animals.size() >= 0)
                    aPlayer.Animals.remove(tempAnswer - 1);
            }
        }


    }

}
