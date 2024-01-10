package interfaces.services;

import java.util.List;

public interface CommandCRUD<Commande>{


    public void addCommande(Commande element);
    public void deleteCommande(int id);
    public List<Commande> getAllCommande();
    public Commande getByIdCommande(int id);

}
