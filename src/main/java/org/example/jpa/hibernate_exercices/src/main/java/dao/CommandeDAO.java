package dao;

import interfaces.Repository;
import models.Commande;
import models.Produit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class CommandeDAO implements Repository<Commande> {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    Session session = sessionFactory.openSession();
    @Override
    public void add(Commande element) {
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        session.beginTransaction();
        Commande commandeToRemove = session.load(Commande.class, id);
        session.delete(commandeToRemove);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Commande> getAll() {
        Query<Commande> query = session.createQuery("from Commande ");

        List<Commande> commandeList = query.list();

        for (Commande c : commandeList
        ) {
            System.out.println("commande => " + c);
        }
        return commandeList;
    }

    @Override
    public Commande getById(int id) {
        Commande commandeToSearch = session.load(Commande.class, id);
        return commandeToSearch;
    }
}
