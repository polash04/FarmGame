package com.company;

import java.sql.Statement;
import java.util.Scanner;

public class Utility {
    //Gets an Input from the player and makes sure it is an integer
    public static int GetInt() {

        Scanner scan = new Scanner(System.in);
        String tempAnswer = null;

        //Loop until answer is an int
        while (true) {
            tempAnswer = scan.next(); // Get input
            if (IsInt(tempAnswer)) {//check if answer is int
                break; //exit the loop
            }
            System.out.println("Invalid Answer"); // if not an int print invalid answer
        }

        return Integer.parseInt(tempAnswer); // return the answer as an int
    }

    //same as GetInt() but with bounds(inclusive)
    public static int GetIntBetween(int min, int max) {

        //Loop until the answer is between the bounds
        while (true) {
            int tempAnswer = GetInt(); // Gets an int from the player
            if (tempAnswer >= min && tempAnswer <= max) {//check if int is between bounds
                return tempAnswer; //returns the answer
            }
            System.out.println("Invalid Answer");// if not between the bounds, print invalid answer
        }

    }

    //Writes out a series of options and lets the player choose one. Inteded for use with a switch statement
    public static int GiveOptions(String... someStrings) { //... allows for an undetermined number of inputs, ex: GiveOptions("A", "B") and GiveOptions("A", "B", "C") are both valid
        for (int i = 0; i < someStrings.length; i++) { // loop through all options
            System.out.println("[" + (i + 1) + "]" + someStrings[i]); //Write out each option with the number ex: "[0] Feed Animals"
        }

        return GetIntBetween(1, someStrings.length) - 1; // return an answer within the bounds of the number of options
    }

    //Checks if a string can be converted to an int
    public static boolean IsInt(String aString) {

        if (aString == null) { // if string is null return false
            return false;
        }

        try {//try to convert the string to an int
            int i = Integer.parseInt(aString);
        } catch (NumberFormatException nfe) {//return false if number cannot be converted
            return false;
        }
        return true; // if catch didn't happen, return true. This means it is an int.
    }

    public static int ChooseAnimal(Player aPlayer, boolean anAddDoneFlag) {
        aPlayer.DisplayAnimalsIndexed();
        //Print the done option with the appropriate number if done should be added
        if (anAddDoneFlag)
            System.out.println("[" + (aPlayer.Animals.size() + 1) + "]" + "Done");
        //Get an answer between the bounds, the ? operator works as an if-statement, if done has been added increase the bounds by 1
        return Utility.GetIntBetween(1, aPlayer.Animals.size() + (anAddDoneFlag ? 1 : 0))-1;
    }



}
