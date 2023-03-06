package stu.najah.se.sql.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.Navigator;
import stu.najah.se.sql.entity.CustomerEntity;

public class CustomerDAO
        implements DAO<CustomerEntity> {

    /**
     * @return all recorded customers
     */
    public ObservableList<CustomerEntity> getAll() {
        var session = Navigator.getSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(CustomerEntity.class);
        query.from(CustomerEntity.class);
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
