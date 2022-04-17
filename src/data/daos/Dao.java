package data.daos;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();

    T findById(Integer pk);

    void insert(T item);

    Boolean update(T item);

    boolean delete(Integer pk);
}
