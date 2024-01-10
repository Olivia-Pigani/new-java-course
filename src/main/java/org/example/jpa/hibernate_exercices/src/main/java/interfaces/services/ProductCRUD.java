package interfaces.services;

import java.util.List;

public interface ProductCRUD<Product>{

    public void addProduct(Product element);
    public void deleteProduct(int id);
    public List<Product> getAll();
    public Product getById(int id);



}
