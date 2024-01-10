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
    public void delete(int id) {
        produitDAO.delete(id);

    }

    @Override
    public List<Produit> getAll() {
      List<Produit> produitList = produitDAO.getAll();
      return produitList;
    }


    @Override
    public Produit getById(int id) {
        Produit produit = produitDAO.getById(id);
        return produit;
    }

    public void deleteProductByBrand(String brand) {
        produitDAO.deleteProductByBrand(brand);
    }

    public List<Produit> productListByBrand(String brand) {
      List<Produit> produitList =  produitDAO.productListByBrand(brand);
      return produitList;
    }

    public double averageProductPrice() {
       Double averageProductPrice = produitDAO.averageProductPrice();
       return averageProductPrice;
    }

    public int stockValueByBrand(String brand) {
      Integer stockValue = produitDAO.stockValueByBrand(brand);
      return stockValue;

    }

    public List<Produit> getProductrefsWithLowerStock(int stockValue) {
       List<Produit> produitList = produitDAO.getProductrefsWithLowerStock(stockValue);
       return produitList;
    }

    public List<Produit> getProductBetweenDate(Date dateOne, Date dateTwo) {
      List<Produit> produitList =  produitDAO.getProductBetweenDate(dateOne,dateTwo);
      return produitList;
    }
}
