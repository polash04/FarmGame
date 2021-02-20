package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;

enum Gender {
    Male,
    Female
}

public abstract class Animal {
    public String myName;
    public int myHealth = 100;
    public int myAge = 0;
    public int myMaxAge;
    public int myCost = 0;
    public int BabyCount = 1;
    Gender myGender;
    public FoodType[] FoodTypes;

    public Animal(boolean aBabyFlag) {
        Scanner tempScan = new Scanner(System.in);

        if (aBabyFlag) {
            switch (Game.Rng.nextInt(2)) {
                case 0:
                    myGender = Gender.Male;
                    break;
                case 1:
                    myGender = Gender.Female;
                    break;
            }
            System.out.println("It's a " + myGender.toString() + "!");

        } else {
            //Prompts the player to select a gender for their animal
            System.out.println("Please select a gender:");
            switch (Utility.GiveOptions("Male", "Female")) {
                case 0:
                    //Male
                    myGender = Gender.Male;
                    break;
                case 1:
                    //Female
                    myGender = Gender.Female;
                    break;
            }
        }


        //Prompts the player to choose a name
        System.out.print("Please enter a name: ");
        myName = tempScan.next();
    }

    public static void CrateBabies(Player aPlayer, Animal anAnimal) {

        //Get a random baby count between 1(inclusive) and BabyCount(inclusive)
        int tempBabyCount = Game.Rng.nextInt(anAnimal.BabyCount) + 1; // +1 to always give at least 1 baby, not -1 to BabyCount to make it inclusive
        //print how many babies were obtained
        System.out.println("You obtained " + tempBabyCount + " of the animal \"" + anAnimal.getClass().getSimpleName() + "\".");

        //Instantiates new animal of anAnimal's type and gives it to the player
        //to create an object this way you have to catch all exceptions, otherwise there will be compile errors
        try {
            //Animal constructor parameters classes
            Class[] cArg = new Class[1];
            //boolean is a parameter
            cArg[0] = boolean.class;

            //Get the correct animal class
            Class<? extends Animal> tempClass = anAnimal.getClass();


            //Instantiate [tempBabyCount] new animals of the correct type as babies and give to aPlayer
            for (int i = 0; i < tempBabyCount; i++)
                aPlayer.Animals.add(tempClass.getDeclaredConstructor(cArg).newInstance(true));

        } catch (NoSuchMethodException e) {
            System.out.println(e.toString());
        } catch (SecurityException e) {
            System.out.println(e.toString());
        } catch (InstantiationException e) {
            System.out.println(e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public boolean Update(Player aPlayer) {
        int tempHealthDecrease = Game.Rng.nextInt(31 - 10) + 10;
        myHealth -= tempHealthDecrease; // 31 is max bound 10 is lower bound
        System.out.println("    " + myName + " lost " + tempHealthDecrease + "% health");

        myAge++;
        //if age is above max age remove the animal from the player
        if (myAge > myMaxAge) {
            aPlayer.Animals.remove(this);
            System.out.println("    " + myName + " Died...");
            return true;
        }
        return false;

    }

    public int GetValue() {
        // Calculates the current cost of the animal, decreases to a minimum of 20% of the base cost depending on the current age of the also multiplies by health
        //Health casted to float to prevent integer multiplication
        return (int) (myCost * ((float) myHealth / 100f) * (Math.max(1 - ((float) myAge / (float) myMaxAge), 0.2f)));
    }

}
