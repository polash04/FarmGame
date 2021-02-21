package com.company;

import jdk.jshell.execution.Util;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    Player[] myPlayers;
    public static Random Rng = new Random();

    public Game() {
        //Gets how many rounds to play
        System.out.print("How many rounds? (5-30)");
        int tempRoundCount = Utility.GetIntBetween(5, 30);

        //Gets how many players are playing
        System.out.print("How many Players? (1-4)");
        myPlayers = new Player[Utility.GetIntBetween(1, 4)];

        Scanner tempScan = new Scanner(System.in);
        //Create the players
        for (int i = 0; i < myPlayers.length; i++) {
            myPlayers[i] = new Player();
            System.out.print("Please choose a name: ");
            myPlayers[i].Name = tempScan.next();
        }

        //Loop [number of rounds] times
        for (int i = 0; i < tempRoundCount; i++) {
            //Loop through all players each round
            for (int j = 0; j < myPlayers.length; j++) {
                //if player is bankrupt, skip that player
                if(myPlayers[j].Bankrupt)
                    continue;


                //Print which player is playing right now
                System.out.println(myPlayers[j].Name + ":");

                //Display the current player's info
                myPlayers[j].DisplayInfo();

                //Prompts the player to choose what they want to do
                System.out.println("What do you want to do?");

                switch (Utility.GiveOptions("Buy animals", "Buy food", "Feed animals", "Mate animals", "Sell animals")) {
                    case 0:
                        //Buy Animals
                        Store.BuyAnimal(myPlayers[j]);
                        break;

                    case 1:
                        //Buy Food
                        Store.BuyFood(myPlayers[j]);
                        break;

                    case 2:
                        //Feed Animals
                        FeedAnimals(myPlayers[j]);
                        break;

                    case 3:
                        //Mate
                        if(!MateAnimals(myPlayers[j])){
                            j--;
                            continue;
                        }
                        break;

                    case 4:
                        //Sell Animals
                        Store.SellAnimal(myPlayers[j]);
                        break;
                }

            }
            //Update at the end of each round
            Update();
            System.out.println("Turn passed... (" + (i +1) + "/" + tempRoundCount + ")");

            //Check if a player has gone bankrupt
            for(int j = 0; j < myPlayers.length; j++){
                if(myPlayers[j].Money < 100 && myPlayers[j].Animals.size() == 0){
                    System.out.println(myPlayers[j].Name +" has gone bankrupt...");
                    myPlayers[i].Bankrupt = true;
                }
            }

        }
        System.out.println("The game has ended, all animals have been sold...");
        //Sell all animals
        for(int i = 0; i < myPlayers.length; i++){
            for(int j = 0; j<myPlayers[i].Animals.size(); j++){
                myPlayers[i].Money += myPlayers[i].Animals.get(j).GetValue();
            }
        }
        System.out.print("The winner is");
        //Add suspense by waiting 1 second between each dot
        for(int i = 0; i < 3; i++){
            System.out.print(".");
            try
            {
                //Pause current thread for 1000 milliseconds
                Thread.sleep(1000);
            }
            //To use Thread.Sleep you have to catch InterruptedException
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        //Determine winner
        int tempWinner = -1;
        int tempHighestSum = 0;
        for(int i = 0; i < myPlayers.length; i++){
            if(myPlayers[i].Money > tempHighestSum){
                tempWinner = i;
                tempHighestSum = myPlayers[i].Money;
            }
        }
        //Display winner
        System.out.println(myPlayers[tempWinner].Name + " with " + myPlayers[tempWinner].Money + "Schmeckles!");
        System.out.println("Congratulations!!");

        //print the rest of the player's money
        for(int i = 0; i < myPlayers.length; i++){
            if(i == tempWinner)
            {
                continue;
            }
            System.out.println(myPlayers[i].Name + " money: " + myPlayers[i].Money);
        }


    }

    boolean MateAnimals(Player aPlayer) {
        while(true){
            //Get first animal
            aPlayer.DisplayAnimalsIndexed();
            System.out.println("[" + (aPlayer.Animals.size() + 1) + "]" + "Back");
            System.out.print("Choose the first animal:");

            //Get an answer between the bounds
            int tempAnswer = Utility.GetIntBetween(1, aPlayer.Animals.size() +1) - 1;
            //Check if "Back" was selected
            if (tempAnswer == aPlayer.Animals.size())
                return false; //return false to let the player choose a different option

            Animal tempAnimal1 = aPlayer.Animals.get(tempAnswer);

            //Get second animal
            System.out.print("Choose the second animal:");
            //Get an answer between the bounds
            tempAnswer = Utility.GetIntBetween(1, aPlayer.Animals.size() +1) - 1;
            //Check if "Back" was selected
            if (tempAnswer == aPlayer.Animals.size())
                return false;

            Animal tempAnimal2 = aPlayer.Animals.get(tempAnswer);

            //If the two chosen animals cant mate, also covers the case when you have chosen the same animal 2 times since gender will be the same
            if (tempAnimal1.myGender == tempAnimal2.myGender|| tempAnimal1.getClass() != tempAnimal2.getClass()) {
                System.out.println("Cannot mate these...");
            } else { // if the two animals can mate
                //50% chance, flips a coin, if correct side is rolled add a new animal of the type of the ones bred
                if (Rng.nextInt(2) == 0) {
                    System.out.println("Congratulations, your animals succeeded in creating offspring!");
                    Animal.CrateBabies(aPlayer, tempAnimal1);


                } else { // if the animals didn't make a baby
                    System.out.println("The animals mated but didn't make any babies...");
                }
                return true; //return true to continue to next turn
            }
        }


    }

    void FeedAnimals(Player aPlayer) {
        //Loop forever
        while(true){
            //Display owned food
            aPlayer.DisplayFood();
            System.out.println("Which animal do you want to feed?");
            //display owned animals with index
            aPlayer.DisplayAnimalsIndexed();
            //Print done option
            System.out.println("[" + (aPlayer.Animals.size() + 1) + "]" + "Done");

            //Get an answer between the bounds
            int tempAnswer = Utility.GetIntBetween(1, aPlayer.Animals.size() +1) - 1;
            //Check if "Done" was selected
            if (tempAnswer == aPlayer.Animals.size())
                return;
            //Save the animal in a variable
            Animal tempAnimal = aPlayer.Animals.get(tempAnswer);
            System.out.println("What do you want to feed " + tempAnimal.myName);

            //Print allowed food type options
            for (int i = 0 ; i < tempAnimal.FoodTypes.length; i++){
                System.out.println("[" +(i+1) + "]" + tempAnimal.FoodTypes[i].toString() + "(you have " + aPlayer.Food[tempAnimal.FoodTypes[i].getValue()] + "Kg)");
            }
            //Get an answer between the bounds
            tempAnswer = Utility.GetIntBetween(1, tempAnimal.FoodTypes.length +1) - 1;
            //save the food type in a variable
            FoodType tempFoodType = tempAnimal.FoodTypes[tempAnswer];

            //Ask how much player wants to feed
            System.out.println("How many Kg of " + tempFoodType.toString() + " do you want to feed " + tempAnimal.myName + "?");
            System.out.println("You have " + aPlayer.Food[tempFoodType.getValue()] + " Kg of this food type.");
            //Get an answer
            tempAnswer = Utility.GetIntBetween(0,aPlayer.Food[tempFoodType.getValue()]);

            //Display effect
            tempAnimal.myHealth += tempAnswer * 10;
            //make sure the animal cant have more than 100% health
            if(tempAnimal.myHealth >100)
                tempAnimal.myHealth = 100;

            System.out.println("You fed " + tempAnimal.myName + " " + tempAnswer + "Kg of " + tempFoodType.toString() + "(+" + (tempAnswer * 10) + "% health)");
            System.out.println("-" + tempAnswer + "Kg of " + tempFoodType.toString());
            aPlayer.Food[tempFoodType.getValue()] -= tempAnswer;
        }

    }

    void Update() {
        //loop through all players and update all animals the player owns
        for (int i = 0; i < myPlayers.length; i++) {
            System.out.println(myPlayers[i].Name +":");
            for (int j = 0; j < myPlayers[i].Animals.size(); j++) {
                //If the animal dies, print what animal died
                if (myPlayers[i].Animals.get(j).Update(myPlayers[i])) {
                    System.out.println(myPlayers[i].Name + "'s" + myPlayers[i].Animals.get(j).getClass().getSimpleName() + "\"" + myPlayers[i].Animals.get(j).myName + "\" died...");
                }
            }
        }

    }


}
