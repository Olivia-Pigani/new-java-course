package interfaces.services;

import java.util.List;

public interface AdresseCRUD<Adresse> {

    public void addAdress(Adresse element);
    public void deleteAdress(int id);
    public List<Adresse> getAllAdresse();
    public Adresse getByIdAdresse(int id);


}
