package com.company;

public class Pig extends Animal{
    public static int Cost = 175;
    public Pig(boolean aBabyFlag) {
        super(aBabyFlag);
        myCost = Cost;
        myMaxAge = 14;
        BabyCount = 5;
    }
}
