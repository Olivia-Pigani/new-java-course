package org.example.controller;

import org.example.impl.CategoryDaoImpl;
import org.example.impl.PersonDAOImpl;
import org.example.impl.TaskDAOImpl;
import org.example.model.Category;
import org.example.model.Person;
import org.example.model.Task;
import org.example.model.TaskInfo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ToDoListAppConsole {

    private static EntityManagerFactory entityManagerFactory;
    private static TaskDAOImpl taskDAO;

    private static PersonDAOImpl personDAO;


    private static CategoryDaoImpl categoryDao;

    public static void main() {
        entityManagerFactory = Persistence.createEntityManagerFactory("todolist");
        taskDAO = new TaskDAOImpl(entityManagerFactory);
        personDAO = new PersonDAOImpl(entityManagerFactory);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("#### To Do List ####");
            System.out.println("1. Ajouter une tâche à la liste");
            System.out.println("2. Afficher toutes les tâches de la liste");
            System.out.println("3. Marquer une tâche comme terminée");
            System.out.println("4. Supprimer une tâche de la liste");
            System.out.println("5. Ajouter une personne");
            System.out.println("6. Afficher les tâches d'une personne");
            System.out.println("7. Supprimer une personne");

            System.out.println("8. Ajouter une catégorie");
            System.out.println("9. Supprimer une catégorie");
            System.out.println("10. Afficher les tâches d'une catégorie");
            System.out.println("11. Ajouter une taches à une catégorie");
            System.out.println("12. Supprimer une taches à une catégorie");



            System.out.println("0. Quitter l'application");
            System.out.println("Choix : ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choice){
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    displayTasks();
                    break;
                case 3:
                    markTaskAsCompleted(scanner);
                    break;
                case 4:
                    deleteTask(scanner);
                    break;
                case 5:
                    addPerson(scanner);
                    break;
                case 6:
                    displayTasksPerson(scanner);
                    break;
                case 7:
                    deleteUser(scanner);
                    break;

                case 8:
                    addACategory(scanner);
                    break;

                case 9:
                    deleteACategory(scanner);
                    break;



                case 0:
                    System.out.println("Bye");
                    entityManagerFactory.close();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");

            }

        }while (choice != 0);
    }

    private static void deleteACategory(Scanner scanner) {

        try {
            System.out.println("QueL est l'id de la categorie souhaitez vous delete ?");
            Long categoryId = scanner.nextLong();
            scanner.nextLine();

            Category categoryToRemove = categoryDao.getOneCategoryById(categoryId);

            if (categoryToRemove != null){
                categoryDao.deleteCategory(categoryId);
            }

            System.out.println(" la catégorie a été enlevée ! ");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void addACategory(Scanner scanner) {
        System.out.println("Quel est le nom de la catégorie ?");
        String categoryName = scanner.nextLine();

        Category newCategory = new Category();
        newCategory.setName(categoryName);

        categoryDao.addCategory(newCategory);




    }

    private static void addTask(Scanner scanner){
        System.out.println("Entrez le titre de la tâche : ");
        String title = scanner.nextLine();

        System.out.println("Entrez la description de la tâche : ");
        String description = scanner.nextLine();

        System.out.println("Date limite de la tâche : (dd.MM.yyyy)");
        String dueDateStr = scanner.nextLine();

        LocalDate dueDate = LocalDate.parse(dueDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Priorité de la tâche : ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Entrez l'ID de la personne pour cette tâche : ");
        Long personId = scanner.nextLong();

        // Creation de la tache
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);

        //Creation de la taskinfo
        TaskInfo taskInfo = new TaskInfo(description,dueDate,priority);

        // Mise en relation
        task.setTaskInfo(taskInfo);
        taskInfo.setTask(task);

        if(taskDAO.addTask(task,personId)){
            System.out.println("Tâche ajoutée avec succès !");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void displayTasks() {
        List<Task> tasks = taskDAO.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche trouvée.");
        } else {
            System.out.println("=== Liste des tâches ===");
            for (Task task : tasks) {
                System.out.println("###########");
                System.out.println(task.getId() + ". " + task.getTitle() + " (" + (task.isCompleted() ? "Terminée" : "En cours") + ")");
                System.out.println(task.getTaskInfo().toString());
                System.out.println("###########");
            }
        }
    }

    private static void deleteTask(Scanner scanner){
        System.out.println("Entrez l'ID de la tâche à supprimer : ");
        Long taskId  = scanner.nextLong();
        scanner.nextLine();

        if (taskDAO.deleteTask(taskId)){
            System.out.println("Suppression OK");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void markTaskAsCompleted(Scanner scanner){
        System.out.println("Entrez l'ID de la tâche à supprimer : ");
        Long taskId  = scanner.nextLong();
        scanner.nextLine();

        if (taskDAO.markTaskAsCompleted(taskId)){
            System.out.println("Modification OK");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void addPerson(Scanner scanner){
        System.out.println("Entrer le nom de la personne : ");
        String personName = scanner.nextLine();
        Person person = new Person(personName);
        personDAO.addPerson(person);
        System.out.println("Personne ajoutée");
    }

    private static void displayTasksPerson(Scanner scanner){
        System.out.println("Entrez l'ID de la personne  : ");
        Long personId  = scanner.nextLong();
        scanner.nextLine();

        List<Task> tasks = taskDAO.gettasksByPersonId(personId);
        System.out.println("Tâches de la personne avec l' ID : "+ personId + " : ");
        for (Task t : tasks){
            System.out.println(t.getId() + ". " + t.getTitle() + " (" + (t.isCompleted() ? "Terminée" : "En cours") + ")");
        }
    }

    private static void deleteUser(Scanner scanner){
        System.out.println("Entrez l'ID de la personne à supprimer : ");
        Long personId  = scanner.nextLong();
        scanner.nextLine();
        personDAO.deletePerson(personId);
        System.out.println("Personne supprimée");

    }
}
