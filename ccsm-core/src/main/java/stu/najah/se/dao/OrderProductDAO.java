package stu.najah.se.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.entity.OrderProductEntity;

public class OrderProductDAO extends FullDAO<OrderProductEntity> {

    /**
     * Constructs a new OrderProductDAO instance.
     */
    public OrderProductDAO() {
        super(OrderProductEntity.class);
    }

    /**
     * @param orderId which all the order product entities share
     * @return list of all the order products entities
     */
    public ObservableList<OrderProductEntity> getAll(int orderId) {
        return FXCollections.observableArrayList(getWithCondition((builder, query, root) ->
                builder.equal(root.get("orderId"), orderId)));
    }
}
