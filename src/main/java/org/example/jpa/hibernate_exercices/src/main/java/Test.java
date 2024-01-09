import models.Produit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {

////////////////////////////////////////////////////////////////////
//    Créer cinq produits,
//    Afficher les informations du produit dont id = 2,
//    Supprimer le produit dont id = 3,
//    Modifier les informations du produit dont id = 1,
///////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws ParseException {


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        //    Créer cinq produits,
        session.beginTransaction();
        Produit produit1 = new Produit();
        produit1.setPrix(45);
        produit1.setStock(25);
        produit1.setMarque("marc");
        produit1.setDateAchat(new Date());
        produit1.setReference("blah blah");

        Produit produit2 = new Produit();
        produit2.setPrix(45);
        produit2.setStock(25);
        produit2.setMarque("marc");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateAchat = formatter.parse("10-05-2010");
        produit2.setDateAchat(dateAchat);

        produit2.setReference("blah blah");

        Produit produit3 = new Produit();
        produit3.setPrix(45);
        produit3.setStock(25);
        produit3.setMarque("marc");
        produit3.setDateAchat(new Date());
        produit3.setReference("blah blah");

        Produit produit4 = new Produit();
        produit4.setPrix(120);
        produit4.setStock(25);
        produit4.setMarque("marc");
        produit4.setDateAchat(new Date());
        produit4.setReference("blah blah");

        Produit produit5 = new Produit();
        produit5.setPrix(150);
        produit5.setStock(25);
        produit5.setMarque("marc");
        produit5.setDateAchat(new Date());
        produit5.setReference("blah blah");

        session.save(produit1);
        session.save(produit2);
        session.save(produit3);
        session.save(produit4);
        session.save(produit5);

        session.getTransaction().commit();
        session.close();


        //    Afficher les informations du produit dont id = 2,

        Produit produitToSearch = session.load(Produit.class, 2);
        System.out.println("Le produit dont l'id est 2 est : " + produitToSearch);

//            Supprimer le produit dont id = 3,
        session.beginTransaction();
        Produit produitToRemove = session.load(Produit.class, 3);
        System.out.println("le produit dont l'id est 3 est " + produitToRemove);
        session.delete(produitToRemove);
        session.getTransaction().commit();
        session.close();

        System.out.println("le produit dont l'id est 3 est " + produitToRemove);


        //  Modifier les informations du produit dont id = 1,
        session.beginTransaction();
        Produit produitToUpdate = session.load(Produit.class, 1);
        System.out.println("Le produit dont l'id est 1 :  " + produitToUpdate);
        produitToUpdate.setReference("updated ref");
        produitToUpdate.setMarque("updated marque");
        produitToUpdate.setPrix(10);
        produitToUpdate.setStock(0);
        session.update(produitToUpdate);
        session.getTransaction().commit();
        session.close();


        System.out.println("Le produit dont l'id est 1 :  " + produitToUpdate);

///////////////////////////////////////////////////////////////////////////////////////
////        Afficher la totalité des produits
////        Afficher la liste des produits dont le prix est supérieur à 100 euros
////        Afficher la liste des produits achetés entre deux dates.
///////////////////////////////////////////////////////////////////////////////////////

        //        Afficher la totalité des produits


        Query<Produit> produitQuery = session.createQuery("from Produit ");

        List<Produit> produitList = produitQuery.list();

        for (Produit p : produitList
        ) {
            System.out.println("produit => " + p);
        }


        //        Afficher la liste des produits dont le prix est supérieur à 100 euros


        Query<Produit> allProductsOver100 = session.createQuery("from Produit where prix > 100");
        List<Produit> over100List = allProductsOver100.list();

        for (Produit p : over100List
        ) {
            System.out.println("produit > 100€ => " + p);
        }

        // Afficher la liste des produits achetés entre deux dates.
       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
            String dateOneStr = "10-02-1995";
            String dateTwoStr = "10-02-2011";
            Date dateOne = formatter.parse(dateOneStr);
            Date dateTwo = formatter.parse(dateTwoStr);

            Query<Produit> produitBetweenDate = session.createQuery("from Produit where dateAchat > :dateOne AND dateAchat < :dateTwo");
            produitBetweenDate.setParameter("dateOne", dateOne);
            produitBetweenDate.setParameter("dateTwo", dateTwo);

            for (Produit p : produitBetweenDate.list()
            ) {
                System.out.println(p);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            sessionFactory.close();
        }





    }
}
