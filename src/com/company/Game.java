package com.company;

public class Game {
    Player[] myPlayers;

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
                        MateAnimals(myPlayers[j]);
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

    void MateAnimals(Player aPlayer){
        //not implemented
    }

    void FeedAnimals(Player aPlayer){
        //Not implemented
    }

    void Update() {
        //loop through all players and update all animals the player owns
        for (int i = 0; i < myPlayers.length; i++) {
            for (int j = 0; j < myPlayers[i].Animals.size(); j++) {
                //If the animal dies, print what animal died
                if(myPlayers[i].Animals.get(j).Update(myPlayers[i])){
                    System.out.println("Player " + i + "'s" + myPlayers[i].Animals.get(j).getClass().getSimpleName() + "\"" + myPlayers[i].Animals.get(j).myName + "\" died..." );
                }
            }
        }

    }


}
