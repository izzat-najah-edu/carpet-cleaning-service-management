package stu.najah.se.dao;

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
                    Database.class.getResource("stu/najah/se/dao/hibernate.cfg.xml"));
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

    /**
     * Executes the given SessionUsage functional interface within a try-with-resources block,
     * ensuring the session is properly closed after the operation.
     *
     * @param usage The SessionUsage functional interface to be executed with the session
     * @param <T>   The type of the result returned from the session operation
     * @return The result of the session operation
     */
    static <T> T useSession(SessionUsage<T> usage) {
        try (var session = sessionFactory.openSession()) {
            return usage.perform(session);
        }
    }

    /**
     * A functional interface to be used with the useSession method.
     * Provides an abstract perform method to execute operations on the session.
     *
     * @param <T> The type of the result returned from the session operation
     */
    @FunctionalInterface
    interface SessionUsage<T> {
        T perform(Session session);
    }
}
