package stu.najah.se.core.service;

import stu.najah.se.core.EntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A generic class for observing changes in a specific entity.
 * The class maintains a reference to the observed entity and notifies listeners when the entity is changed or cleared.
 * This class is useful when working with entities in a system that needs to react to their state changes.
 *
 * @param <T> The type of the entity being observed.
 */
public class ObservedEntity<T> {

    private final List<EntityListener<T>> listeners = new ArrayList<>();

    private T entity;

    /**
     * Retrieves the current observed entity.
     *
     * @return An Optional containing the current observed entity, or empty if no entity is set.
     */
    public Optional<T> get() {
        return Optional.ofNullable(entity);
    }

    /**
     * Sets the observed entity and notifies listeners about the change.
     * If the provided entity is null, the current entity is cleared.
     *
     * @param entity The entity to set as the observed entity.
     */
    public void set(T entity) {
        if (entity != null) {
            this.entity = entity;
            notifyChange();
        } else {
            clear();
        }
    }

    /**
     * Clears the observed entity and notifies listeners about the clearing.
     */
    public void clear() {
        this.entity = null;
        notifyClear();
    }

    /**
     * Adds a listener to the list of listeners that will be notified when the entity changes or is cleared.
     *
     * @param listener The EntityListener to add.
     */
    public void addListener(EntityListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener from the list of listeners.
     *
     * @param listener The EntityListener to remove.
     */
    public void removeListener(EntityListener<T> listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all registered listeners that the observed entity has changed.
     */
    private void notifyChange() {
        listeners.forEach(listener -> listener.onEntityChanged(entity));
    }

    /**
     * Notifies all registered listeners that the observed entity has been cleared.
     */
    private void notifyClear() {
        listeners.forEach(EntityListener::onEntityCleared);
    }
}
