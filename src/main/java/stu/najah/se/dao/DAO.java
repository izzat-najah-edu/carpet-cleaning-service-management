package stu.najah.se.dao;

import javafx.collections.ObservableList;

public interface DAO<T> {
    boolean update(T data);
    boolean insert(T data);
    boolean delete(T data);
    ObservableList<T> getAll();
}
