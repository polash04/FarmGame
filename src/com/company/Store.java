package com.company;

import java.util.Scanner;

public class Store {

    //Checks if the player can afford a price
    static boolean CanAfford(Player aPlayer, int aCost){
        if(aPlayer.Money < aCost){
            System.out.println("You cannot afford that!");
            return false;
        }
        else
            return true;

    }

    public static void BuyAnimal(Player aPlayer) {

        boolean tempDoneFlag = false;
        //While not done
        do{
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
                    if(!CanAfford(aPlayer, Chicken.Cost))
                        break;

                    tempAnimal = new Chicken();
                    System.out.println("-" + Chicken.Cost + " Schmeckles");
                    aPlayer.Money -= Chicken.Cost;
                    break;
                case 1:
                    if(!CanAfford(aPlayer, Cow.Cost))


                    tempAnimal = new Cow();
                    System.out.println("-" + Cow.Cost + " Schmeckles");
                    aPlayer.Money -= Cow.Cost;
                    break;
                case 2:
                    if(!CanAfford(aPlayer, Pig.Cost))
                        break;

                    tempAnimal = new Pig();
                    System.out.println("-" + Pig.Cost + " Schmeckles");
                    aPlayer.Money -= Pig.Cost;
                    break;
                case 3:
                    if(!CanAfford(aPlayer, Sheep.Cost))
                        break;

                    tempAnimal = new Sheep();
                    System.out.println("-" + Sheep.Cost + " Schmeckles");
                    aPlayer.Money -= Sheep.Cost;
                    break;
                case 4:
                    if(!CanAfford(aPlayer, Horse.Cost))
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
        }while(!tempDoneFlag);


        System.out.println("Shopkeep: Pleasure doing business with you!\n");

    }



}
