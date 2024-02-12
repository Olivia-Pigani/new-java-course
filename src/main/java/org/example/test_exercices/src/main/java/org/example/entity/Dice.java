package org.example.entity;

import java.util.Random;

public class Dice {

private Random random;
    public Dice(){
        random = new Random();
    }

    public getValue getRandom() {
        return random;
    }
}
