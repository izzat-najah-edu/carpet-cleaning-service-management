package stu.najah.se.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "admin", schema = "carpet_cleaning_service_management")
public class AdminEntity {
    @Id
    @Column(name = "username", nullable = false, length = 256)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 256)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdminEntity that)) {
            return false;
        }
        return Objects.equals(username, that.username)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
