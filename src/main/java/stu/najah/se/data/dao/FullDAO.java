
package stu.najah.se.data.dao;

import org.hibernate.Session;
import stu.najah.se.gui.Prompter;

import java.util.function.BiConsumer;

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
     * A failure message is prompted if the operation fails.
     *
     * @param object to be updated in the database
     * @return whether the operation succeeded
     */
    public final boolean update(T object) {
        return performTransaction(object, Session::merge);
    }

    /**
     * Attempts to insert the given object into the database.
     * A failure message is prompted if the operation fails.
     *
     * @param object to be inserted in the database
     * @return whether the operation succeeded
     */
    public final boolean insert(T object) {
        return performTransaction(object, Session::persist);
    }

    /**
     * Attempts to delete the given object from the database.
     * A failure message is prompted if the operation fails.
     *
     * @param object to be deleted from the database
     * @return whether the operation succeeded
     */
    public final boolean delete(T object) {
        return performTransaction(object, Session::remove);
    }

    /**
     * Performs a database transaction on the given object using the specified operation.
     * The transaction is wrapped in a try-with-resources block to ensure proper session
     * management. If an exception occurs during the transaction, it is rolled back,
     * and an error message is displayed using the Prompter. Otherwise, the transaction
     * is committed.
     *
     * @param object    the object to perform the transaction on
     * @param operation a BiConsumer functional interface representing the database operation
     *                  to perform; it accepts a Session and the object as arguments
     * @return true if the transaction was successful, false otherwise
     */
    protected boolean performTransaction(T object, BiConsumer<Session, T> operation) {
        var session = Database.createSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            operation.accept(session, object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Prompter.error(e);
            return false;
        }
    }
}
