package com.company;

public class Chicken extends Animal{
    public static int Cost = 100;
    public Chicken(boolean aBabyFlag) {
        super(aBabyFlag);
        myCost = Cost;
        myMaxAge = 10;
        BabyCount = 4;
    }
}
