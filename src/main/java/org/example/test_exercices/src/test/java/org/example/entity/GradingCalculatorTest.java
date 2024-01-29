package org.example.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GradingCalculatorTest {


    @Test
    void testGrade1(){
        //Arrange
        GradingCalculator gradingCalculator =  new GradingCalculator();

        //Act
        gradingCalculator.setScore(95);
        gradingCalculator.setAttendancePercentage(90);
        char grade = gradingCalculator.getGrade();


        //Assert
        Assertions.assertEquals('A',grade);
    }

    @Test
    void testGrade2(){
        //Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();

        //Act
        gradingCalculator.setScore(85);
        gradingCalculator.setAttendancePercentage(90);
        char grade = gradingCalculator.getGrade();

        //Assert
        Assertions.assertEquals('B', grade);
    }


    @Test
    void testGrade3(){
        //Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();

        //Act
        gradingCalculator.setScore(65);
        gradingCalculator.setAttendancePercentage(90);
        char grade = gradingCalculator.getGrade();

        //Assert
        Assertions.assertEquals('C', grade);
    }

    @Test
    void testGrade4(){
        //Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();

        //Act
        gradingCalculator.setScore(95);
        gradingCalculator.setAttendancePercentage(55);
        char grade = gradingCalculator.getGrade();

        //Assert
        Assertions.assertEquals('F', grade);
    }

}
