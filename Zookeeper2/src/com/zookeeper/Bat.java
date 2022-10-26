package com.zookeeper;
public class Bat extends Mammal{

    public Bat(){
        super(300);
    }
    
    void fly(){
        System.out.println("swoosh");
        energyLevel -= 50;
        displayEnergy();
    }

    void eatHumans(){
        System.out.println("Eating a human increases energy by 25");
        energyLevel += 25;
        displayEnergy();
    }

    void attackTown(){
        System.out.println("Attacking a town reduces energy by 100");
        energyLevel -= 100;
        displayEnergy();
    }
    
}