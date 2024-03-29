package stu.najah.se.core.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer", schema = "carpet_cleaning_service_management")
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 256)
    private String name;
    @Basic
    @Column(name = "phone", length = 10)
    private String phone;
    @Basic
    @Column(name = "address", length = 256)
    private String address;
    @Basic
    @Column(name = "email", length = 256)
    private String email;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String phone, String address, String email) {
        setName(name);
        setPhone(phone);
        setAddress(address);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAllBasic(CustomerEntity customer) {
        setName(customer.getName());
        setPhone(customer.getPhone());
        setAddress(customer.getAddress());
        setEmail(customer.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerEntity that)) {
            return false;
        }
        return id == that.id
                && Objects.equals(name, that.name)
                && Objects.equals(phone, that.phone)
                && Objects.equals(address, that.address)
                && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
