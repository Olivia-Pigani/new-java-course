import dao.ProduitDAO;
import service.ProduitService;

public class Main {


    ProduitDAO produitDAO = new ProduitDAO();
    ProduitService produitService = new ProduitService(produitDAO);
    IHM ihm = new IHM(produitDAO, produitService);

}
