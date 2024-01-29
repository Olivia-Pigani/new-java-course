package org.example.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FibTest {

//    Le résultat n’est pas vide
//    Le résultat correspond à une liste qui contient {0}

    @Test
    void rangeOneNotEmpty(){
        //Arrange and Act
        Fib fib = new Fib(1);

        // Assert
        Assertions.assertNotNull(fib);


    }

    @Test
    void rangeOneContain0(){
        //Arrange
        Fib fib = new Fib(1);

        //Assert
        List<Integer> result = fib.getFibSeries();
        Assertions.assertTrue(result.contains(0));
    }

//    Lors du déclanchement de la fonction getFibSeries() avec un Range de 6
//    Le résultat contient le chiffre 3
//    Le résultat contient 6 éléments
//    Le résultat n’a pas le chiffre 4 en son sein
//    Le résultat correspond à une liste qui contient {0, 1, 1, 2, 3, 5}
//    Le résultat est trié de façon ascendance


    @Test
    void rangeSixContainThree(){
        //Arrange and Act
        Fib fib = new Fib(6);

        //Assert
        List<Integer> result = fib.getFibSeries();
        Assertions.assertTrue(result.contains(3));
    }

    @Test
    void rangeSixContainSixElements(){
        //Arrange and Act
        Fib fib = new Fib(6);

        //Assert
        List<Integer> result = fib.getFibSeries();
        Assertions.assertEquals(6,result.size());
    }
    @Test
    void rangeSixNotContain4(){
        //Arrange and Act
        Fib fib = new Fib(6);

        //Assert
        List<Integer> result = fib.getFibSeries();
        Assertions.assertFalse(result.contains(4));
    }
    @Test
    void rangeSixNotContainManyElements(){
        //Arrange and Act
        Fib fib = new Fib(6);

        //Assert
        List<Integer> result = fib.getFibSeries();
        List<Integer> verify = new ArrayList<>();
        verify.add(0);
        verify.add(1);
        verify.add(1);
        verify.add(2);
        verify.add(3);
        verify.add(5);
        Assertions.assertEquals(result,verify);
    }

    @Test
    void rangeSixTheResultIsAscendant(){
        //Arrange And Act
        Fib fib = new Fib(6);

        //Assert
        List<Integer> result = fib.getFibSeries();
        for (int i = 2; i<result.size()-1;i++){
            Assertions.assertTrue(result.get(i)<result.get(i+1));
        }

    }

}
