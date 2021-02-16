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


        //Prompts the playe rto choose a name and make sure the player didn't input the wrong name
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

    public void SetName(String aName){
        myName = aName;
    }

    public void Update(){
        myHealth -= myRng.nextInt(31-10) + 10; // 31 is max bound 10 is lower bound
        myAge++;
    }
    public int GetValue(){
        // Calculates the current cost of the animal, decreases to a minimum of 20% of the base cost depending on the current age of the animal
        return (int)(myCost * ( Math.max(1-(myAge/myMaxAge), 0.2f)));
    }

}
