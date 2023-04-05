
package stu.najah.se.sql.dao;

import stu.najah.se.gui.Prompter;
import stu.najah.se.sql.Database;

/**
 * Data Access Object: a layer that separates
 * the SQL operations from any other service using it, like JavaFX.
 * This is a template DAO class with 3 default methods: update, insert, delete.
 *
 * @param <T> an entity class (Admin, Customer...)
 */
abstract class DAO<T> {

    /**
     * Attempts to update the given object in the database.
     * The object is found recognized by using the identifier.
     * A prompt message is shown and handled graphically if the operation fails.
     * No further exceptions thrown whether it fails or succeeds.
     * @param object to be updated in the database
     * @return whether the operation failed or succeeded
     */
    public final boolean update(T object) {
        var session = Database.createSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.merge(object);
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

    /**
     * Attempts to insert the given object in the database.
     * A prompt message is shown and handled graphically if the operation fails.
     * No further exceptions thrown whether it fails or succeeds.
     * @param object to be inserted in the database
     * @return whether the operation failed or succeeded
     */
    public final boolean insert(T object) {
        var session = Database.createSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.persist(object);
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

    /**
     * Attempts to delete the given object from the database.
     * The object is found recognized by using the identifier.
     * A prompt message is shown and handled graphically if the operation fails.
     * No further exceptions thrown whether it fails or succeeds.
     * @param object to be deleted from the database
     * @return whether the operation failed or succeeded
     */
    public final boolean delete(T object) {
        var session = Database.createSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.remove(object);
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
