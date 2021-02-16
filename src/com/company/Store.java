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

        boolean tempDoneFlag = false;
        //While not done
        do {
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
                case 0:
                    if (!CanAfford(aPlayer, Chicken.Cost))
                        break;

                    tempAnimal = new Chicken();
                    System.out.println("-" + Chicken.Cost + " Schmeckles");
                    aPlayer.Money -= Chicken.Cost;
                    break;
                case 1:
                    if (!CanAfford(aPlayer, Cow.Cost))


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
                    tempDoneFlag = true;
                    break;
            }
            //Only add the animal if it isn't null
            if (tempAnimal != null) {
                aPlayer.Animals.add(tempAnimal);
            }
        } while (!tempDoneFlag);


        System.out.println("Shopkeep: Pleasure doing business with you!\n");

    }


    public static void BuyFood(Player aPlayer) {
        do{
            aPlayer.DisplayInfo();
            FoodType tempFoodType = FoodType.Silage;
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
                    return;
            }
            System.out.println("How many Kg of " + tempFoodType.toString() + " do you want to buy?");
            int tempAnswer = Utility.GetInt();
            int tempCost = 0;

            if(tempFoodType == FoodType.Silage)
                tempCost = Silage.Cost;
            else if(tempFoodType == FoodType.Seeds)
                tempCost = Seeds.Cost;
            else if(tempFoodType == FoodType.Carrots)
                tempCost = Carrots.Cost;

            if(CanAfford(aPlayer, tempAnswer * tempCost )){
                aPlayer.Food[tempFoodType.getValue()] += tempAnswer;
                System.out.println("-" + tempAnswer * tempCost + " Schmeckles");
                aPlayer.Money -= tempAnswer * tempCost;
            }
        }while(true);

    }


}
