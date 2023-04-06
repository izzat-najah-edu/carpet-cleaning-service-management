package stu.najah.se.data.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.data.entity.OrderEntity;
import stu.najah.se.data.entity.OrderViewEntity;

public class OrderDAO extends DAO<OrderEntity> {

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
     * @param customerName which all the order view entities share
     * @return all order view entities with the given customerName
     */
    public ObservableList<OrderViewEntity> getAll(String customerName) {
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
