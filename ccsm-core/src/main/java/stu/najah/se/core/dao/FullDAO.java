package stu.najah.se.core.dao;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import stu.najah.se.core.DatabaseOperationException;

/**
 * A DAO class that supports DML Operations.
 * It has 3 default methods: update, insert, delete.
 *
 * @param <T> an entity class (Admin, Customer...)
 */
abstract class FullDAO<T> extends DAO<T> {

    /**
     * Constructs a new DAO instance for the given entity class.
     *
     * @param entityClass The entity class for which this DAO is responsible.
     */
    protected FullDAO(Class<T> entityClass) {
        super(entityClass);
    }

    /**
     * Attempts to update the given object in the database.
     *
     * @param object to be updated in the database
     * @throws DatabaseOperationException if the transaction fails
     */
    public void update(T object) throws DatabaseOperationException {
        performTransaction(object, Session::merge);
    }

    /**
     * Attempts to insert the given object into the database.
     *
     * @param object to be inserted in the database
     * @throws DatabaseOperationException if the transaction fails
     */
    public void insert(T object) throws DatabaseOperationException {
        performTransaction(object, Session::persist);
    }

    /**
     * Attempts to delete the given object from the database.
     *
     * @param object to be deleted from the database
     * @throws DatabaseOperationException if the transaction fails
     */
    public void delete(T object) throws DatabaseOperationException {
        performTransaction(object, Session::remove);
    }

    /**
     * Performs a database transaction on the given object using the specified operation.
     * The transaction is wrapped in a try-with-resources block to ensure proper session
     * management. If an exception occurs during the transaction, it is rolled back,
     * and the exception is thrown. Otherwise, the transaction is committed.
     *
     * @param object    the object to perform the transaction on
     * @param operation a TransactionOperation functional interface representing the database operation
     *                  to perform; it accepts a Session and the object as arguments
     * @throws DatabaseOperationException if the transaction fails
     */
    protected void performTransaction(T object, TransactionOperation<T> operation)
            throws DatabaseOperationException {
        var session = Database.createSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            operation.perform(session, object);
            transaction.commit();
        } catch (RollbackException e) {
            transaction.rollback();
            throw new DatabaseOperationException(e);
        } catch (PersistenceException e) {
            throw new DatabaseOperationException(e);
        }
    }

    /**
     * A functional interface that represents a database transaction operation
     * to be performed on an object of type T, using a Hibernate Session.
     * The perform method should contain the implementation of the transaction operation,
     * and it may throw a checked exception.
     *
     * @param <T> the type of the object involved in the transaction operation
     */
    @FunctionalInterface
    public interface TransactionOperation<T> {

        /**
         * Performs a database transaction operation on the given object using the provided Hibernate Session.
         * This method should contain the implementation of the transaction operation.
         *
         * @param session the Hibernate Session to be used for the transaction operation
         * @param object  the object of type T involved in the transaction operation
         */
        void perform(Session session, T object);
    }
}
