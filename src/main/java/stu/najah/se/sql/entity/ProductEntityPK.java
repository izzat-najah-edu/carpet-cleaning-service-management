package stu.najah.se.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ProductEntityPK implements Serializable {
    @Column(name = "number", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    @Column(name = "customer_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductEntityPK that)) {
            return false;
        }
        return number == that.number
                && customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + customerId;
        return result;
    }
}
