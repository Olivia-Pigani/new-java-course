package dao;

import interfaces.Repository;
import models.Adresse;
import models.Produit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class AdresseDAO implements Repository<Adresse> {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    Session session = sessionFactory.openSession();

    @Override
    public void add(Adresse element) {
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        session.beginTransaction();
        Adresse adresseToRemove = session.load(Adresse.class, id);
        session.delete(adresseToRemove);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Adresse> getAll() {
        Query<Adresse> query = session.createQuery("from Adresse");

        List<Adresse> adresseList = query.list();

        for (Adresse a : adresseList
        ) {
            System.out.println("adresse => " + a);
        }
        return adresseList;
    }

    @Override
    public Adresse getById(int id) {
        Adresse adresseToSearch = session.load(Adresse.class, id);
        return adresseToSearch;
    }
}
