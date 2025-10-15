package net.rubrion.common.api.event.exception;

/**
 * Exception thrown when an error occurs during event handler processing.
 *
 * @author LeyCM
 * @since 1.1.2
 */
public class ProcessHandlerException extends RuntimeException {

    /**
     * Constructs a new ProcessHandlerException with the specified detail message.
     *
     * @param message the detail message
     * @author LeyCM
     * @since 1.1.2
     */
    public ProcessHandlerException(String message) {
        super(message);
    }

    /**
     * Constructs a new ProcessHandlerException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     * @author LeyCM
     * @since 1.1.2
     */
    public ProcessHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ProcessHandlerException with the specified cause.
     *
     * @param cause the cause of the exception
     * @author LeyCM
     * @since 1.1.2
     */
    public ProcessHandlerException(Throwable cause) {
        super(cause);
    }
}
