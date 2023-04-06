package stu.najah.se.data.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.data.entity.ProductEntity;

public class ProductDAO extends DAO<ProductEntity> {

    /**
     * @param customerId which all the products share
     * @return all products with the given customerId
     */
    public ObservableList<ProductEntity> getAll(int customerId) {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(ProductEntity.class);
        var root = query.from(ProductEntity.class);
        query.where(builder.equal(root.get("customerId"), customerId));
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
