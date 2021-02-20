package com.company;

public class Horse extends Animal{
    public static int Cost = 250;
    public Horse(boolean aBabyFlag) {
        super(aBabyFlag);
        myCost = Cost;
        myMaxAge = 18;
        BabyCount = 1;
        FoodTypes = new FoodType[]{FoodType.Silage, FoodType.Carrots};
    }
}
