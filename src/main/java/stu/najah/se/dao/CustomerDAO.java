package stu.najah.se.dao;

import jakarta.persistence.criteria.CriteriaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.Navigator;
import stu.najah.se.sql.entity.CustomerEntity;

import java.util.List;

public class CustomerDAO
    implements DAO<CustomerEntity> {

    @Override
    public boolean update(CustomerEntity data) {
        var session = Navigator.getSession();
        var transaction = session.getTransaction();
        try {
            transaction.begin();
            session.merge(data);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            Navigator.prompt(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean insert(CustomerEntity data) {
        var session = Navigator.getSession();
        var transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(data);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            Navigator.prompt(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(CustomerEntity data) {
        var session = Navigator.getSession();
        var transaction = session.getTransaction();
        try {
            transaction.begin();
            session.remove(data);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            Navigator.prompt(e.getMessage());
            return false;
        }
    }

    @Override
    public ObservableList<CustomerEntity> getAll() {
        var session = Navigator.getSession();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> query = builder.createQuery(CustomerEntity.class);
        query.from(CustomerEntity.class);
        List<CustomerEntity> list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
