package stu.najah.se.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.entity.CustomerEntity;

public class CustomerDAO extends FullDAO<CustomerEntity> {

    /**
     * Constructs a new CustomerDAO instance.
     */
    public CustomerDAO() {
        super(CustomerEntity.class);
    }

    /**
     * @return all recorded customers
     */
    public ObservableList<CustomerEntity> getAll() {
        return FXCollections.observableArrayList(getWithCondition(null));
    }

    /**
     * @param nameSubstring of the customer
     * @return all customers which names contain the given substring
     */
    public ObservableList<CustomerEntity> getAll(String nameSubstring) {
        return FXCollections.observableArrayList(getWithCondition((builder, query, root) ->
                builder.like(root.get("name"), "%" + nameSubstring + "%")));
    }
}
