package com.company;

public class Sheep extends Animal{
    public static int Cost = 200;

    public Sheep(boolean aBabyFlag) {
        super(aBabyFlag);
        myCost = Cost;
        myMaxAge = 15;
        BabyCount = 2;
        FoodTypes =  new FoodType[]{FoodType.Silage, FoodType.Seeds};
        VetCost = 90;
    }
}
