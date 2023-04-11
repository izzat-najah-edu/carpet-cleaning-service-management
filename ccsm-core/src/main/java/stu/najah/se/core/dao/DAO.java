package stu.najah.se.core.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

/**
 * Data Access Object: a layer that separates
 * the SQL operations from any other service using it, like JavaFX.
 *
 * @param <T> an entity class (Admin, Customer...)
 */
abstract class DAO<T> {

    /**
     * The class representing the entity type for which this DAO is responsible
     */
    private final Class<T> entityClass;

    /**
     * Constructs a new DAO instance for the given entity class.
     *
     * @param entityClass The entity class for which this DAO is responsible.
     */
    protected DAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * @param identifier of the entity
     * @return the entity object in the database with the given identifier,
     * or null if it's not found
     */
    public T get(Object identifier) {
        try (var session = Database.createSession()) {
            return session.find(entityClass, identifier);
        }
    }

    /**
     * Retrieves a list of entities from the database based on the provided condition.
     * This method creates a session, builds a query, and applies the given condition
     * to the query, if any. The query is then executed, and the results are returned.
     *
     * @param condition A PredicateFunction representing the condition to be applied to the query.
     * @return A list of entities of type T that match the given condition,
     * or all entities if no condition is provided.
     */
    protected List<T> getWithCondition(PredicateFunction<T> condition) {
        try (var session = Database.createSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(entityClass);
            var root = query.from(entityClass);
            if (condition != null) {
                query.where(condition.apply(builder, query, root));
            }
            return session.createQuery(query).getResultList();
        }
    }

    /**
     * A functional interface representing a function that generates a {@link Predicate}
     * for a given {@link CriteriaBuilder}, {@link CriteriaQuery}, and {@link Root}.
     *
     * @param <T> the type of the entity to which the predicate will be applied
     */
    @FunctionalInterface
    interface PredicateFunction<T> {

        /**
         * Applies this function to the given {@link CriteriaBuilder}, {@link CriteriaQuery}, and {@link Root}.
         *
         * @param builder the {@link CriteriaBuilder} used for constructing the {@link Predicate}
         * @param query   the {@link CriteriaQuery} that represents the query being built
         * @param root    the {@link Root} that defines the entity type and its corresponding metadata
         * @return the {@link Predicate} generated by applying this function to the given parameters
         */
        Predicate apply(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root);
    }
}