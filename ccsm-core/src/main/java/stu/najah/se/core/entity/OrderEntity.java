package stu.najah.se.core.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "`order`", schema = "carpet_cleaning_service_management")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "customer_id")
    private Integer customerId;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<OrderProductEntity> orderProductsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderEntity that)) {
            return false;
        }
        return Objects.equals(id, that.id)
                && Objects.equals(customerId, that.customerId)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    public Collection<OrderProductEntity> getOrderProductsById() {
        return orderProductsById;
    }

    public void setOrderProductsById(Collection<OrderProductEntity> orderProductsById) {
        this.orderProductsById = orderProductsById;
    }
}
