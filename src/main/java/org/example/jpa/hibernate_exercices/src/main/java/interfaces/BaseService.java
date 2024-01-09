package interfaces;

import java.util.List;

public interface BaseService<T>{

    public boolean add(T element);
    public boolean delete(int id);
    public List<T> getAll();
    public T getById(int id);



}
