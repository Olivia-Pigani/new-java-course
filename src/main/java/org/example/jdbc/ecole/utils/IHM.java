package org.example.jdbc.ecole.utils;

import org.example.jdbc.ecole.service.Ecole;

import java.util.Scanner;

public class IHM {
    public static Scanner scanner = new Scanner(System.in);
    private static Ecole ecole;


    public static void main(String[] args) {


        printMenu();
        System.out.println();


    }

    private static void printMenu() {
        int choix;
        System.out.println("=== Menu ===");
        System.out.println("1 - Avoir tout les elèves");
        System.out.println("2 - Avoir tout les elèves d'une classe ");
        System.out.println("3 - Supprimer un élève ");
        System.out.println("4 - Ajouter un élève");
        System.out.println("Quel est votre choix ?");
        choix = scanner.nextInt();

        switch (choix) {
            case 1:
                Ecole.allStudents();
                break;
            case 2:
                System.out.println("quelle classe ?");
                int classNb = scanner.nextInt();
                Ecole.allStudentsByClass(classNb);
                break;
            case 3:
                System.out.println("quelle éleve ?");
                int studentId = scanner.nextInt();
                Ecole.deleteAStudent(studentId);
                break;
            case 4:
                Ecole.insertStudent();

        }


    }




}
