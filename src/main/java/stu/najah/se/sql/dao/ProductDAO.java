package stu.najah.se.sql.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.Navigator;
import stu.najah.se.sql.entity.ProductEntity;

public class ProductDAO
        implements DAO<ProductEntity> {

    /**
     * @param customerId which all the products share
     * @return all products with the given customerId
     */
    public ObservableList<ProductEntity> getAll(int customerId) {
        var session = Navigator.getSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(ProductEntity.class);
        var root = query.from(ProductEntity.class);
        query.where(builder.equal(root.get("customerId"), customerId));
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
