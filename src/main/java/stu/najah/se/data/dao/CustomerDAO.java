package stu.najah.se.data.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.data.entity.CustomerEntity;

public class CustomerDAO extends DAO<CustomerEntity> {

    /**
     * @param id of the customer
     * @return the customer entity with the given id.
     * or null if it's not found
     */
    public CustomerEntity get(int id) {
        var session = Database.createSession();
        var customer = session.get(CustomerEntity.class, id);
        session.close();
        return customer;
    }

    /**
     * @return all recorded customers
     */
    public ObservableList<CustomerEntity> getAll() {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(CustomerEntity.class);
        query.from(CustomerEntity.class);
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }

    /**
     * @param nameSubstring of the customer
     * @return all customers which names contain the given substring
     */
    public ObservableList<CustomerEntity> getAll(String nameSubstring) {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(CustomerEntity.class);
        var root = query.from(CustomerEntity.class);
        query.where(builder.like(root.get("name"), "%" + nameSubstring + "%"));
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
