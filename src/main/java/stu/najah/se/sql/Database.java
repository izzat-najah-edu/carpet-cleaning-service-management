package stu.najah.se.sql;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.hibernate.cfg.Configuration;
import stu.najah.se.sql.entity.AdminEntity;

/**
 * Utility class for the database.
 * It must be initialized once before use.
 * It's configured using Hibernate Framework.
 * Contains a method to create sessions to the database.
 */
public final class Database {

    private Database() {
    }

    /**
     * Database initialized flag
     */
    private static boolean initialized = false;
    /**
     * The database connector object, to generate the sessions
     */
    private static SessionFactory sessionFactory;

    /**
     * Creates the session factory object.
     * Should be called before using the other methods.
     * Calls after the first time have no effect.
     */
    public static void initialize() {
        // can only be initialized once
        if (initialized) {
            return;
        }
        // initialize the session factory
        try {
            var configuration = new Configuration().configure(
                    Database.class.getResource("hibernate.cfg.xml"));
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        // close the session factory after the application is shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(sessionFactory::close));
        // database initialized
        initialized = true;
    }

    /**
     * Generates a new session object.
     * Always close the session after, and don't keep references to it.
     * The session is meant to be disregarded as soon as the transaction is finished
     *
     * @return a new session object
     */
    public static Session createSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
