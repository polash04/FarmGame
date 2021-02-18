package com.company;

import jdk.jshell.execution.Util;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

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

        //Create the players
        for (int i = 0; i < myPlayers.length; i++) {
            myPlayers[i] = new Player();
        }

        //Loop [number of rounds] times
        for (int i = 0; i < tempRoundCount; i++) {

            //Loop through all players each round
            for (int j = 0; j < myPlayers.length; j++) {
                //Print which player is playing right now
                System.out.println("Player " + (j + 1) + ":");

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
                return true; //return tru to continue to next turn
            }
        }


    }

    void FeedAnimals(Player aPlayer) {
        //Not implemented
    }

    void Update() {
        //loop through all players and update all animals the player owns
        for (int i = 0; i < myPlayers.length; i++) {
            for (int j = 0; j < myPlayers[i].Animals.size(); j++) {
                //If the animal dies, print what animal died
                if (myPlayers[i].Animals.get(j).Update(myPlayers[i])) {
                    System.out.println("Player " + i + "'s" + myPlayers[i].Animals.get(j).getClass().getSimpleName() + "\"" + myPlayers[i].Animals.get(j).myName + "\" died...");
                }
            }
        }

    }


}
