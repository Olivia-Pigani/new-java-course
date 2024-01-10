package dao;

import interfaces.Repository;
import models.Commentaire;
import models.Produit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class CommentaireDAO implements Repository<Commentaire> {
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    Session session = sessionFactory.openSession();
    @Override
    public void add(Commentaire element) {
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        session.beginTransaction();
        Commentaire commentaireToRemove = session.load(Commentaire.class, id);
        session.delete(commentaireToRemove);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Commentaire> getAll() {
        Query<Commentaire> query = session.createQuery("from Commentaire ");

        List<Commentaire> commentaireList = query.list();

        for (Commentaire c : commentaireList
        ) {
            System.out.println("commentaire => " + c);
        }
        return commentaireList;
    }

    @Override
    public Commentaire getById(int id) {
        Commentaire comToSearch = session.load(Commentaire.class, id);
        return comToSearch;
    }
}
