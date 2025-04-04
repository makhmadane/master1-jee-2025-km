package src.main.dao;

import java.util.List;

public interface Repository<T> {
    public void insert(T t);
    public boolean delete(int id);
    public List<T> getAll();
    public void update(T t);
    public T getById(int id);
}
