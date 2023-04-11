package stu.najah.se.dao;

import stu.najah.se.entity.OrderProductEntity;

import java.util.List;

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
    public List<OrderProductEntity> getAll(int orderId) {
        return getWithCondition((builder, query, root) ->
                builder.equal(root.get("orderId"), orderId));
    }
}
