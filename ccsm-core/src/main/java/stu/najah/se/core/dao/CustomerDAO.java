package stu.najah.se.core.dao;

import stu.najah.se.core.entity.CustomerEntity;

import java.util.List;

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
    public List<CustomerEntity> getAll() {
        return getWithCondition(null);
    }

    /**
     * @param nameSubstring of the customer
     * @return all customers which names contain the given substring
     */
    public List<CustomerEntity> getAll(String nameSubstring) {
        return getWithCondition((builder, query, root) ->
                builder.like(root.get("name"), "%" + nameSubstring + "%"));
    }
}
