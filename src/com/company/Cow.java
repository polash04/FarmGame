package com.company;

public class Cow extends Animal{
    public static int Cost = 150;
    public Cow(boolean aBabyFlag) {
        super(aBabyFlag);
        myCost = Cost;
        myMaxAge = 12;
        BabyCount = 3;
    }
}

