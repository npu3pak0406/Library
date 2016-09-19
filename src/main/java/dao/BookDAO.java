package dao;

import java.io.Serializable;
import java.util.List;

public interface BookDAO<T, I extends Serializable> {
    void save(T entity);

    void update(T entity);

    void delete(I id);

    List<T> findAll();

    List<T> findByName(String name);
}
