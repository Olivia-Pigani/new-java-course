package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Car {

private int rentDueIn;
private int condition;

    public int getRentDueIn() {
        return rentDueIn;
    }

    public void setRentDueIn(int rentDueIn) {
        this.rentDueIn = rentDueIn;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void dailyRoutine(){

    }
}
