package stu.najah.se.dao;

import javafx.collections.ObservableList;

/**
 * Data Access Object: a layer that separates
 * the sql operations (Hibernate) and any other service using it, like JavaFX
 * @param <T> an entity class (Admin, Customer...)
 */
public interface DAO<T> {
    boolean update(T data);
    boolean insert(T data);
    boolean delete(T data);
    ObservableList<T> getAll();
}
