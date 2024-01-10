package service;

import dao.CommentaireDAO;
import dao.ImageDAO;
import dao.ProduitDAO;
import interfaces.services.ProductCRUD;
import interfaces.services.CommentaryCRUD;
import interfaces.services.ImageCRUD;
import models.Commentaire;
import models.Image;
import models.Produit;

import java.util.Date;
import java.util.List;

public class ProduitServiceimpl implements ProductCRUD<Produit>, CommentaryCRUD<Commentaire>, ImageCRUD<Image> {

    // PRODUIT IMAGE COMMENTARES

    private ProduitDAO produitDAO;
    private ImageDAO imageDAO;
    private CommentaireDAO commentaireDAO;


    public ProduitServiceimpl(ProduitDAO produitDAO, ImageDAO imageDAO, CommentaireDAO commentaireDAO) {
        this.produitDAO = produitDAO;
        this.imageDAO = imageDAO;
        this.commentaireDAO = commentaireDAO;
    }

    // CRUD PRODUCT

    @Override
    public void addProduct(Produit element) {

    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public List<Produit> getAll() {
        return null;
    }

    @Override
    public Produit getById(int id) {
        return null;
    }


    // CRUD COMMENTARIES

    @Override
    public void addCom(Commentaire element) {

    }

    @Override
    public void deleteCom(int id) {

    }

    @Override
    public List<Commentaire> getAllCom() {
        return null;
    }

    @Override
    public Commentaire getByIdCom(int id) {
        return null;
    }


    // CRUD IMAGES

    @Override
    public void addImage(Image element) {

    }

    @Override
    public void deleteImage(int id) {

    }

    @Override
    public List<Image> getAllImage() {
        return null;
    }

    @Override
    public Image getByIdImage(int id) {
        return null;
    }


    // SPECIAL METHODS

    public void deleteProductByBrand(String brand) {
        produitDAO.deleteProductByBrand(brand);
    }

    public List<Produit> productListByBrand(String brand) {
        List<Produit> produitList = produitDAO.productListByBrand(brand);
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
        List<Produit> produitList = produitDAO.getProductBetweenDate(dateOne, dateTwo);
        return produitList;
    }


}
