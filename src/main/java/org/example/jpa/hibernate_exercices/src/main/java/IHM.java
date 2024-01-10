import dao.ProduitDAO;
import models.Produit;
import service.ProduitService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

    public class IHM {
    private Scanner scanner = new Scanner(System.in);
    private ProduitDAO produitDAO;
    private ProduitService produitService;
    private int choice;
    private boolean run = true;

        public IHM() {
            produitDAO = new ProduitDAO();
            produitService = new ProduitService(produitDAO);
        }

        public void printMenu() {
        while (run) {

            System.out.println("=== MENU ===");
            // exercice1 and 2
            System.out.println("1. add product");
            System.out.println("2. delete product");
            System.out.println("3. get all product");
            System.out.println("4. get one product by id");

            System.out.println("5. get all product's by price range");
            System.out.println("6. get all product's beween dates");

            // exercice3
            System.out.println("7. get all product's number and refs whith lower stock");

            // exercice 4
            System.out.println("8. get stock value by brand");
            System.out.println("9. calculate products average price");
            System.out.println("10. get product list by brand");
            System.out.println("11. delete product by brand");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    getAllProduct();
                    break;
                case 4:
                    getOneProductById();
                    break;
                case 5:
                    getProductByPriceRange();
                    break;
                case 6:
                    getProductBetweenDate();
                    break;
                case 7:
                    getProductrefsWithLowerStock();
                    break;
                case 8:
                    stockValueByBrand();
                    break;
                case 9:
                    averageProductPrice();
                    break;
                case 10:
                    productListByBrand();
                    break;
                case 11:
                    deleteProductByBrand();
                    break;
                case 12:
                    closeAll();
                    run = false;
                    break;
                default:
                    System.out.println("wrong input, retry ! ");
                    printMenu();

            }
        }
    }

        private void closeAll() {
            scanner.close();
        }

        private void deleteProductByBrand() {
        try {
            System.out.println("What is the id of the brand ? ");
            String brand = scanner.nextLine();
            produitService.deleteProductByBrand(brand);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private List<Produit> productListByBrand() {
        try {
            System.out.println("What is the id of the brand ? ");
            String brand = scanner.nextLine();
            List<Produit> productsByBrand =  produitService.productListByBrand(brand);
            return productsByBrand;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private void averageProductPrice() {
        try {
           double averagePrice =  produitService.averageProductPrice();
            System.out.println("the average price is " + averagePrice);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private double stockValueByBrand() {
        System.out.println("What is the id of the brand ? ");
        String brand = scanner.nextLine();
        double Stockvalue = produitService.stockValueByBrand(brand);
        return Stockvalue;
    }

    // retourner les numéros et reference des articles dont le stock est inf au valeur lu au clavier
    private List<Produit> getProductrefsWithLowerStock() {
        try {
            System.out.println(" Enter a stock value, we'll see lower value ! ");
            int stockValue = scanner.nextInt();
            scanner.nextLine();
            List<Produit> produitList = produitService.getProductrefsWithLowerStock(stockValue);
            return produitList;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    private List<Produit> getProductBetweenDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            System.out.println("enter the first date ! Au format dd-MM-yyyy ");
            String dateOneStr = scanner.nextLine();
            Date dateOne = format.parse(dateOneStr);
            System.out.println("enter the second date ! Au format dd-MM-yyyy ");
            String dateTwoStr = scanner.nextLine();
            Date dateTwo = format.parse(dateTwoStr);

           List<Produit> produitList = produitService.getProductBetweenDate(dateOne,dateTwo);
           return produitList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    private List<Produit> getProductByPriceRange() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            System.out.println("enter the first date ! Au format dd-MM-yyyy ");
            String dateOneStr = scanner.nextLine();
            Date dateOne = format.parse(dateOneStr);
            System.out.println("enter the second date ! Au format dd-MM-yyyy ");
            String dateTwoStr = scanner.nextLine();
            Date dateTwo = format.parse(dateTwoStr);

            List<Produit> produitList = produitService.getProductBetweenDate(dateOne,dateTwo);
            return produitList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Produit getOneProductById() {
        try {
            System.out.println("What is the id of the product ? ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Produit produit = produitService.getById(id);
            return produit;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    private List<Produit> getAllProduct() {
        try {
            List<Produit> produitList = produitService.getAll();
            return produitList;
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    private void deleteProduct() {
        try {
            System.out.println("What is the id of the product ? ");
            int id = scanner.nextInt();
            scanner.nextLine();
            produitService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addProduct() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println("What is the brand ?");
            String brand = scanner.nextLine();
            System.out.println("What is the reference ?");
            String reference = scanner.nextLine();
            System.out.println("What is the buy date ? format dd-MM-yyyy");
            String dateStr = scanner.nextLine();
            Date realDate = format.parse(dateStr);
            System.out.println("What is the price ?");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("What is the stock ?");
            int stock = scanner.nextInt();

            Produit newProduct = new Produit(brand,reference,realDate,price,stock);

            produitService.add(newProduct);


        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
