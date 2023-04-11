package stu.najah.se.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;
import stu.najah.se.entity.OrderEntity;
import stu.najah.se.entity.OrderViewEntity;

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
    public ObservableList<OrderEntity> getAll(int customerId) {
        return FXCollections.observableArrayList(getWithCondition((builder, query, root) ->
                builder.equal(root.get("customerId"), customerId)));
    }

    /**
     * @return all order view entities
     */
    public ObservableList<OrderViewEntity> getAllViews() {
        return FXCollections.observableArrayList(orderViewDAO.getWithCondition(null));
    }

    /**
     * @param customerName which all the order view entities share
     * @return all order view entities with the given customerName
     */
    public ObservableList<OrderViewEntity> getAllViews(String customerName) {
        return FXCollections.observableArrayList(orderViewDAO.getWithCondition((builder, query, root) ->
                builder.equal(root.get("customerName"), customerName)));
    }
}
