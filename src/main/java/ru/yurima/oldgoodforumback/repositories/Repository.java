package ru.yurima.oldgoodforumback.repositories;

import java.util.List;
import java.util.Optional;

public interface  Repository<T> {
    void save(T entity);
    Optional<T> findById(long id);
    List<T> findAll();
    void delete(T entity);
    void deleteById(long id);
    void deleteAll();
}
