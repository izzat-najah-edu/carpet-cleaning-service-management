package stu.najah.se.core.dao;

import stu.najah.se.core.entity.OrderProductEntity;
import stu.najah.se.core.entity.OrderProductEntityPK;

import java.util.List;

public class OrderProductDAO extends FullDAO<OrderProductEntity> {

    /**
     * Constructs a new OrderProductDAO instance.
     */
    public OrderProductDAO() {
        super(OrderProductEntity.class);
    }

    /**
     * @param orderId   of the order product
     * @param productId of the order product
     * @return the order product in the database, or null if it's not found
     */
    public OrderProductEntity get(int orderId, int productId) {
        return get(new OrderProductEntityPK(orderId, productId));
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
