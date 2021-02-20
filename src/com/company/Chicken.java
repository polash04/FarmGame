package com.company;

import java.util.ArrayList;

public class Chicken extends Animal{
    public static int Cost = 100;
    public Chicken(boolean aBabyFlag) {
        super(aBabyFlag);
        myCost = Cost;
        myMaxAge = 10;
        BabyCount = 4;
        FoodTypes = new FoodType[]{FoodType.Seeds, FoodType.Carrots};
    }
}
