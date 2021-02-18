package com.company;

import java.util.Random;
import java.util.Scanner;

enum Gender{
    Male,
    Female
}

public abstract class Animal {
    public String myName;
    public int myHealth = 100;
    public int myAge = 0;
    public int myMaxAge;
    public int myCost = 0;
    Gender myGender;

    Random myRng = new Random();

    public Animal(){
        Scanner tempScan = new Scanner(System.in);

        //Prompts the player to select a gender for their animal
        System.out.println("Please select a gender:");
        switch(Utility.GiveOptions("Male", "Female")){
            case 0:
                //Male
                myGender = Gender.Male;
                break;
            case 1:
                //Female
                myGender = Gender.Female;
                break;
        }


        //Prompts the player to choose a name and make sure the player didn't input the wrong name
        boolean tempNameConfirmedFlag = false;
        do{
            System.out.print("Please enter a name: ");
            String tempName = tempScan.next();
            System.out.println("Are you sure you want to name your animal \""+ tempName +"\"?");
            switch(Utility.GiveOptions("Yes", "No")){
                case 0:
                    //Yes
                    tempNameConfirmedFlag = true;
                    myName = tempName;
                    break;
            }

        }while(!tempNameConfirmedFlag);
    }

    public boolean Update(Player aPlayer){
        myHealth -= myRng.nextInt(31-10) + 10; // 31 is max bound 10 is lower bound

        myAge++;
        //if age is above max age remove the animal from the player
        if(myAge > myMaxAge) {
                aPlayer.Animals.remove(this);
                return true;
        }
        return false;

    }
    public int GetValue(){
        // Calculates the current cost of the animal, decreases to a minimum of 20% of the base cost depending on the current age of the also multiplies by health
        //Health casted to float to prevent integer multiplication
        return (int)(myCost * ((float)myHealth/100f) * ( Math.max(1-((float)myAge/(float)myMaxAge), 0.2f)));
    }

}
