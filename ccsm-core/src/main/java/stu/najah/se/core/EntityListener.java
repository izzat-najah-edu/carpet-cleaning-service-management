package stu.najah.se.core;

/**
 * <p>
 * The EntityListener interface defines the contract for classes that wish to be notified
 * when the current entity in a service changes.
 * </p><p>
 * This interface allows implementing classes to react to changes
 * in the entity state and perform any necessary actions.
 * </p>
 * Services accept specific types of EntityListener depending on the type of entity they manage.
 *
 * @param <T> The type of entity being observed by the listener.
 */
public interface EntityListener<T> {
    /**
     * This method is called when a new entity is set in the service. Implementing classes
     * should handle the event and perform any necessary actions, such as updating UI components
     * or performing data processing.
     *
     * @param newEntity The new entity that has been set in the service.
     */
    void onEntityChanged(T newEntity);

    /**
     * This method is called when the entity in the service is cleared or set to null.
     * Implementing classes should handle the event and perform any necessary actions,
     * such as clearing UI components or resetting data structures.
     */
    void onEntityCleared();
}
