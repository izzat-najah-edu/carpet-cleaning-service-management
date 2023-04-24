package stu.najah.se.core.dao;

import org.hibernate.Hibernate;
import stu.najah.se.core.entity.OrderEntity;

import java.util.List;

public class OrderDAO extends FullDAO<OrderEntity> {

    /**
     * Constructs a new OrderDAO instance.
     */
    public OrderDAO() {
        super(OrderEntity.class);
    }

    /**
     * @param identifier of the order
     * @return the order entity with the given id.
     * or null if it's not found
     */
    @Override
    public OrderEntity get(Object identifier) {
        try (var session = Database.createSession()) {
            var order = session.get(OrderEntity.class, identifier);
            if (order != null) {
                Hibernate.initialize(order.getOrderProductsById());
            }
            return order;
        }
    }

    /**
     * @param customerId which all the orders share
     * @return all orders with the given customerId
     */
    public List<OrderEntity> getAll(int customerId) {
        return getWithCondition((builder, query, root) ->
                builder.equal(root.get("customerId"), customerId));
    }
}
