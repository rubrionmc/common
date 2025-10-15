/**
 * RPL-LICENSE NOTICE
 * <br><br>
 * This Sourcecode is under the RPL-LICENSE. <br>
 * License at: <a href="https://github.com/rubrionmc/.github/blob/main/licensens/RUBRION_PUBLIC">GITHUB</a>
 * <br><br>
 * Copyright (c) LeyCM <leycm@proton.me> <br>
 * Copyright (c) maintainers <br>
 * Copyright (c) contributors
 */
package net.rubrion.common.api.event.able;

/**
 * Interface that defines cancelable events.
 * Events implementing this interface can be canceled to prevent further processing.
 *
 * @author LeyCM
 * @since 1.1.2
 */
public interface Cancelable {

    /**
     * Sets the canceled state of this event.
     *
     * @param state {@code true} to cancel the event, {@code false} to allow processing
     */
    void setCanceled(boolean state);

    /**
     * Checks if this event is canceled.
     *
     * @return {@code true} if the event is canceled, {@code false} otherwise
     */
    boolean isCanceled();

    /**
     * Cancels this event by setting the canceled state to {@code true}.
     * This is a convenience method equivalent to {@code setCanceled(true)}.
     *
     * @author LeyCM
     * @since 1.1.2
     */
    default void cancel() {
        setCanceled(true);
    }

    /**
     * Cancels this event with the specified state.
     * This is a convenience method equivalent to {@code setCanceled(state)}.
     *
     * @param state {@code true} to cancel the event, {@code false} to allow processing
     * @author LeyCM
     * @since 1.1.2
     */
    default void cancel(boolean state) {
        setCanceled(state);
    }

}
