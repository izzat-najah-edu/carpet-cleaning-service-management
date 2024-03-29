package stu.najah.se.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class OrderProductEntityPK implements Serializable {
    @Column(name = "order_id", nullable = false)
    @Id
    private int orderId;
    @Column(name = "product_id", nullable = false)
    @Id
    private int productId;

    public OrderProductEntityPK() {
    }

    public OrderProductEntityPK(int orderId, int productId) {
        setOrderId(orderId);
        setProductId(productId);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderProductEntityPK that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId)
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + productId;
        return result;
    }
}
