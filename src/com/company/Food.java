package com.company;

enum FoodType{
    Silage(0),
    Seeds(1),
    Carrots(2);

    //final to not be able to change value after first time
    private final int value;
    //Sets the value
    private FoodType(int value) {
        this.value = value;
    }

    //returns the value
    public int getValue() {
        return value;
    }
}

public abstract class Food {

}
