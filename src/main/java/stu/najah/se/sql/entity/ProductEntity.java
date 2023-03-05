package stu.najah.se.sql.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product", schema = "carpet_cleaning_service_management")
@IdClass(ProductEntityPK.class)
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "number", nullable = false)
    private int number;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id", nullable = false)
    private int customerId;
    @Basic
    @Column(name = "description", nullable = false, length = 256)
    private String description;
    @Basic
    @Column(name = "special_treatment", length = 256)
    private String specialTreatment;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialTreatment() {
        return specialTreatment;
    }

    public void setSpecialTreatment(String specialTreatment) {
        this.specialTreatment = specialTreatment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductEntity that)) {
            return false;
        }
        return number == that.number
                && customerId == that.customerId
                && Objects.equals(description, that.description)
                && Objects.equals(specialTreatment, that.specialTreatment);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + customerId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (specialTreatment != null ? specialTreatment.hashCode() : 0);
        return result;
    }
}
