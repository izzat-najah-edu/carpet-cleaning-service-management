package stu.najah.se.data.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.najah.se.data.entity.OrderProductEntity;
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

    /**
     * @param customerId which all the products share
     * @return all products with the given customerId that are not associated with any order
     */
    public ObservableList<ProductEntity> getAllAvailable(int customerId) {
        var session = Database.createSession();
        var builder = session.getCriteriaBuilder();
        var query = builder.createQuery(ProductEntity.class);
        var root = query.from(ProductEntity.class);
        // check if a product is not in the order_product table
        var subQuery = query.subquery(Long.class);
        var subQueryRoot = subQuery.from(OrderProductEntity.class);
        subQuery.select(builder.count(subQueryRoot));
        subQuery.where(builder.equal(subQueryRoot.get("productId"), root.get("id")));
        // add the subQuery and customerId condition to the main query's condition
        query.where(builder.and(builder.equal(root.get("customerId"), customerId), builder.equal(subQuery, 0L)));
        // query and return it
        var list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
