package com.company;

import java.util.Random;

public abstract class Animal {
    String myName;
    int myHealth = 100;
    int myAge;
    int myMaxAge;

    Random myRng = new Random();

    public void SetName(String aName){
        myName = aName;
    }

    public void Update(){
        myHealth -= myRng.nextInt(31-10) + 10; // 31 is max bound 10 is lower bound
    }

}
