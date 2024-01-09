package service;

import dao.ProduitDAO;
import interfaces.BaseService;
import models.Produit;

import java.util.Date;
import java.util.List;

public class ProduitService implements BaseService<Produit> {

    private ProduitDAO produitDAO;


    public ProduitService(ProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    @Override
    public void add(Produit element) {
        produitDAO.add(element);
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

    public void deleteProductByBrand(String brand) {
    }

    public List<Produit> productListByBrand(String brand) {
    }

    public void averageProductPrice(List<Produit> produitList) {
    }

    public double stockValueByBrand(String brand) {
    }

    public List<Produit> getProductrefsWithLowerStock(int stockValue) {
    }

    public List<Produit> getProductBetweenDate(Date dateOne, Date dateTwo) {
    }
}
