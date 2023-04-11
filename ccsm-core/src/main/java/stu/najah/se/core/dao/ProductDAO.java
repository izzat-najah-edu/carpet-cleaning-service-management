package stu.najah.se.core.dao;

import stu.najah.se.core.entity.OrderProductEntity;
import stu.najah.se.core.entity.ProductEntity;

import java.util.List;

public class ProductDAO extends FullDAO<ProductEntity> {

    /**
     * Constructs a new ProductDAO instance.
     */
    public ProductDAO() {
        super(ProductEntity.class);
    }

    /**
     * @param customerId which all the products share
     * @return all products with the given customerId
     */
    public List<ProductEntity> getAll(int customerId) {
        return getWithCondition((builder, query, root) ->
                builder.equal(root.get("customerId"), customerId));
    }

    /**
     * @param customerId which all the products share
     * @return all products with the given customerId that are not associated with any order
     */
    public List<ProductEntity> getAllAvailable(int customerId) {
        return getWithCondition((builder, query, root) -> {
            // check if a product is not in the order_product table
            var subQuery = query.subquery(Long.class);
            var subQueryRoot = subQuery.from(OrderProductEntity.class);
            subQuery.select(builder.count(subQueryRoot));
            subQuery.where(builder.equal(subQueryRoot.get("productId"), root.get("id")));
            // add the subQuery and customerId condition to the main query's condition
            return builder.and(builder.equal(root.get("customerId"), customerId), builder.equal(subQuery, 0L));
        });
    }
}
