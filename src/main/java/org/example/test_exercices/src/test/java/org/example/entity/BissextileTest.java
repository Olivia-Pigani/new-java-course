package org.example.entity;

import org.junit.jupiter.api.Test;

public class BissextileTest {

    int year = 1996;

    @Test
    void divisibleBy4AndNot100() {

        if (year %  4 == 0 && year % 100 != 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }


    @Test
    void divisibleBy400() {

        if (year % 400 == 0 ) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    @Test
    void divisibleBy4000() {

        if (year % 4000 == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }


}
