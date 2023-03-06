package stu.najah.se.sql.dao;

import stu.najah.se.Navigator;

/**
 * Data Access Object: a layer that separates
 * the sql operations (Hibernate) from any other service using it, like JavaFX
 *
 * @param <T> an entity class (Admin, Customer...)
 */
public interface DAO<T> {
    default boolean update(T data) {
        var session = Navigator.getSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.merge(data);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Navigator.prompt(e.getMessage());
            return false;
        }
    }

    default boolean insert(T data) {
        var session = Navigator.getSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.persist(data);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Navigator.prompt(e.getMessage());
            return false;
        }
    }

    default boolean delete(T data) {
        var session = Navigator.getSession();
        var transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.remove(data);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Navigator.prompt(e.getMessage());
            return false;
        }
    }
}
