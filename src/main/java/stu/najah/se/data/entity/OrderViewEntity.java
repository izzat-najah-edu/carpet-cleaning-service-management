package stu.najah.se.data.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "order_view", schema = "carpet_cleaning_service_management")
public class OrderViewEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    private int orderId;
    @Basic
    @Column(name = "customer_name", nullable = false, length = 256)
    private String customerName;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderViewEntity that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId)
                && Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }
}
