package dao;

import interfaces.Repository;
import models.Produit;

import java.util.List;

public class ProduitDAO implements Repository<Produit> {

    @Override
    public boolean add(Produit element) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Produit> getAll() {
        return null;
    }

    @Override
    public Produit getById(int id) {
        return null;
    }
}
