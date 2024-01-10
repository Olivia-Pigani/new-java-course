package dao;

import interfaces.Repository;
import models.Image;
import models.Produit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class ImageDAO implements Repository<Image> {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    Session session = sessionFactory.openSession();

    @Override
    public void add(Image element) {
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        session.beginTransaction();
        Image imageToRemove = session.load(Image.class, id);
        session.delete(imageToRemove);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Image> getAll() {
        Query<Image> query = session.createQuery("from Image ");

        List<Image> imageList = query.list();

        for (Image i : imageList
        ) {
            System.out.println("image => " + i);
        }
        return imageList;
    }

    @Override
    public Image getById(int id) {
        Image imageToSearch = session.load(Image.class, id);
        return imageToSearch;

    }
}
