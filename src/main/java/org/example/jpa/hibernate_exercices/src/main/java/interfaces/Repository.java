package interfaces;

import java.util.List;

public interface Repository<T> {

    public void add(T element);
    public void delete(int id);
    public List<T> getAll();
    public T getById(int id);




}
