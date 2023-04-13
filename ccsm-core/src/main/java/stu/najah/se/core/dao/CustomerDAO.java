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
     * Searches for a single customer with the given name.
     * Uniqueness is guaranteed, no two customers share a name.
     *
     * @param customerName of the customer
     * @return the found customer with the given name,
     * or null if none were found.
     */
    public CustomerEntity get(String customerName) {
        var list = getWithCondition(((builder, query, root) ->
                builder.equal(root.get("name"), customerName)));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * @return all recorded customers
     */
    public List<CustomerEntity> getAll() {
        return getWithCondition(null);
    }

    /**
     * @param customerNameSubstring of the customer
     * @return all customers which names contain the given substring
     */
    public List<CustomerEntity> getAllLike(String customerNameSubstring) {
        return getWithCondition((builder, query, root) ->
                builder.like(root.get("name"), "%" + customerNameSubstring + "%"));
    }
}
