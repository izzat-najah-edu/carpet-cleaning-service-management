package stu.najah.se.core.dao;

import org.hibernate.Hibernate;
import stu.najah.se.core.entity.OrderEntity;
import stu.najah.se.core.entity.OrderViewEntity;

import java.util.List;

public class OrderDAO extends FullDAO<OrderEntity> {

    private final OrderViewDAO orderViewDAO = new OrderViewDAO();

    static class OrderViewDAO extends DAO<OrderViewEntity> {
        /**
         * Constructs a new OrderViewDAO instance.
         */
        protected OrderViewDAO() {
            super(OrderViewEntity.class);
        }
    }

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

    /**
     * @return all order view entities
     */
    public List<OrderViewEntity> getAllViews() {
        return orderViewDAO.getWithCondition(null);
    }

    /**
     * @param customerName which all the order view entities share
     * @return all order view entities with the given customerName
     */
    public List<OrderViewEntity> getAllViews(String customerName) {
        return orderViewDAO.getWithCondition((builder, query, root) ->
                builder.equal(root.get("customerName"), customerName));
    }
}
