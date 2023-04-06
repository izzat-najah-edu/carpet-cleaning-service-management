package stu.najah.se.data.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.data.entity.OrderProductEntity;

public class OrderProductDAO extends DAO<OrderProductEntity> {

    /**
     * @param orderId which all the order product entities share
     * @return list of all the order products entities
     */
    public ObservableList<OrderProductEntity> getAll(int orderId) {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(OrderProductEntity.class);
        var root = query.from(OrderProductEntity.class);
        query.where(builder.equal(root.get("orderId"), orderId));
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
