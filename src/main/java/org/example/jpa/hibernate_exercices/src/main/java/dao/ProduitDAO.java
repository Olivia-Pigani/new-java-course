package dao;

import interfaces.Repository;
import models.Produit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class ProduitDAO implements Repository<Produit> {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    Session session = sessionFactory.openSession();

    @Override
    public void add(Produit element) {
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        session.beginTransaction();
        Produit produitToRemove = session.load(Produit.class, id);
        session.delete(produitToRemove);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Produit> getAll() {
        Query<Produit> produitQuery = session.createQuery("from Produit ");

        List<Produit> produitList = produitQuery.list();

        for (Produit p : produitList
        ) {
            System.out.println("produit => " + p);
        }
        return produitList;
    }


    @Override
    public Produit getById(int id) {
        Produit produitToSearch = session.load(Produit.class, id);
        return produitToSearch;

    }

    public void deleteProductByBrand(String brand) {
        try {
            session.beginTransaction();
            Query<Produit> query = session.createQuery("from Produit where marque = :brand ");
            query.setParameter("brand", brand);

            List<Produit> allProductByBrand = query.list();


            for (Produit p : allProductByBrand
            ) {
                session.delete(p);

            }
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();

        }


    }

    public List<Produit> productListByBrand(String brand) {
        try {
            Query<Produit> query = session.createQuery("from Produit where marque = :brand");
            query.setParameter("brand", brand);
            List<Produit> produitList = query.list();

            for (Produit p : produitList
            ) {
                System.out.println(p);
            }
            return produitList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public double averageProductPrice() {

try {
    Query<Double> query = session.createQuery(" select avg(p.prix) from Produit p");

    Double averagePrice = query.uniqueResult();
    return averagePrice;
}catch (Exception e){
    e.printStackTrace();
}finally {
    session.close();
}

        System.out.println("There is an error");
        return 0;
    }


    public int stockValueByBrand(String brand) {
        try {
            Query<Integer> query = session.createQuery("select SUM(p.stock) from Produit p ");
            Integer stockValue = query.uniqueResult();
            System.out.println(" the stock value for the brand : " + brand + " is " + stockValue);
            return stockValue;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        System.out.println("There is an error");
        return 0;
    }

    public List<Produit> getProductrefsWithLowerStock(int stockValue) {
        try {
            Query<Produit> query = session.createQuery("from Produit p where p.stock < :stockValue");
            query.setParameter("stockValue",stockValue);
            List<Produit> produitList = query.list();
            for (Produit p:produitList
                 ) {
                System.out.println(p);
            }
            return produitList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    public List<Produit> getProductBetweenDate(Date dateOne, Date dateTwo) {
        try {
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
            session.close();
        }
      return null;
    }
}
