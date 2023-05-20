package stu.najah.se.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for the database.
 * It's configured using Hibernate Framework.
 * Contains a method to create sessions to the database.
 */
final class Database {

    private Database() {
    }

    /**
     * The database connector object, to generate the sessions
     */
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Initialize the session factory
     *
     * @return the session factory object
     */
    private static SessionFactory buildSessionFactory() {
        try {
            var configuration = new Configuration().configure(
                    Database.class.getResource("hibernate.cfg.xml"));
            var sessionFactory = configuration.buildSessionFactory();
            // close the session factory after the application is shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(sessionFactory::close));
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Generates a new session object.
     * Always close the session after, and don't keep references to it.
     * The session is meant to be disregarded as soon as the transaction is finished
     *
     * @return a new session object
     */
    static Session createSession() throws HibernateException {
        return sessionFactory.openSession();
    }

}
