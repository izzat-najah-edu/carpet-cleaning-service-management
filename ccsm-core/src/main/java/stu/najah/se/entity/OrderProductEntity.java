package stu.najah.se.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "order_product", schema = "carpet_cleaning_service_management")
@IdClass(OrderProductEntityPK.class)
public class OrderProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private int orderId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Basic
    @Column(name = "special_treatment", length = 256)
    private String specialTreatment;
    @Basic
    @Column(name = "finished")
    private Byte finished;
    @Basic
    @Column(name = "price")
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false,
            insertable = false, updatable = false)
    private OrderEntity orderByOrderId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false,
            insertable = false, updatable = false)
    private ProductEntity productByProductId;

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

    public String getSpecialTreatment() {
        return specialTreatment;
    }

    public void setSpecialTreatment(String specialTreatment) {
        this.specialTreatment = specialTreatment;
    }

    public Byte getFinished() {
        return finished;
    }

    public void setFinished(Byte finished) {
        this.finished = finished;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderProductEntity that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId)
                && Objects.equals(productId, that.productId)
                && Objects.equals(specialTreatment, that.specialTreatment)
                && Objects.equals(finished, that.finished)
                && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + productId;
        result = 31 * result + (specialTreatment != null ? specialTreatment.hashCode() : 0);
        result = 31 * result + (finished != null ? finished.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public OrderEntity getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(OrderEntity orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {
        this.productByProductId = productByProductId;
    }
}
