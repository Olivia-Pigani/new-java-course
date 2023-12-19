package org.example.jdbc.labo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        SimpleDateFormat rightFormatDate = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("dites moi une date !");
        String theDate = scanner.nextLine();


        try {
            Date finalForùatDate = rightFormatDate.parse(theDate);
            System.out.println("succes, there is the date : " + finalForùatDate);
        }catch (ParseException e){
            System.out.println("invalid format !");
        }



    }


}
