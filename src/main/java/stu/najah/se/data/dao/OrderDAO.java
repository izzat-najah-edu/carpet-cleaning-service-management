package stu.najah.se.data.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.data.entity.OrderEntity;
import stu.najah.se.data.entity.OrderViewEntity;

public class OrderDAO extends DAO<OrderEntity> {

    /**
     * @param id of the order
     * @return the order entity with the given id.
     * or null if it's not found
     */
    public OrderEntity get(int id) {
        var session = Database.createSession();
        var order = session.get(OrderEntity.class, id);
        session.close();
        return order;
    }

    /**
     * @param customerId which all the orders share
     * @return all orders with the given customerId
     */
    public ObservableList<OrderEntity> getAll(int customerId) {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(OrderEntity.class);
        var root = query.from(OrderEntity.class);
        query.where(builder.equal(root.get("customerId"), customerId));
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }

    /**
     * @return all order view entities
     */
    public ObservableList<OrderViewEntity> getAllViews() {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(OrderViewEntity.class);
        query.from(OrderViewEntity.class);
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }

    /**
     * @param customerName which all the order view entities share
     * @return all order view entities with the given customerName
     */
    public ObservableList<OrderViewEntity> getAllViews(String customerName) {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(OrderViewEntity.class);
        var root = query.from(OrderViewEntity.class);
        query.where(builder.equal(root.get("customerName"), customerName));
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
