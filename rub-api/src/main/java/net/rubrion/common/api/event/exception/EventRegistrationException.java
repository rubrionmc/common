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
package net.rubrion.common.api.event.exception;

/**
 * Thrown when an event handler cannot be registered due to configuration issues,
 * security restrictions, or other registration-related problems.
 *
 * @author LeyCM
 * @since 1.1.2
 */
public class EventRegistrationException extends RuntimeException {

    /**
     * Constructs a new EventRegistrationException with the specified detail message.
     *
     * @param message the detail message
     */
    public EventRegistrationException(String message) {
        super(message);
    }

    /**
     * Constructs a new EventRegistrationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public EventRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}

