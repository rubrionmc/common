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
package net.rubrion.common.api.event.handler;

import net.rubrion.common.api.event.Event;
import net.rubrion.common.api.event.able.Monitorable;
import net.rubrion.common.api.event.exception.EventRegistrationException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * Represents information about an event handler, including the event type it handles
 * and its priority level.
 * <p>
 * This record encapsulates the metadata extracted from methods annotated with {@link Handler},
 * providing a type-safe way to manage event handler registration and execution order.
 * </p>
 *
 * @param type  the class of the event that this handler processes, must extend {@link Event}
 * @param level the priority level of this handler, determining execution order
 *
 * @author LeyCM
 * @since 1.1.2
 */
public record HandlerInfo(Class<? extends Event> type, HandlerPriority level) {

    /**
     * Creates a new {@code HandlerInfo} instance from a method annotated with {@link Handler}.
     * <p>
     * This factory method extracts the event type from the first parameter of the method
     * and the priority level from the {@link Handler} annotation. It performs validation
     * to ensure the method parameter is a valid event type.
     * </p>
     *
     * @param method the method annotated with {@link Handler}, must not be {@code null}
     * @return a new {@code HandlerInfo} instance with the extracted event type and priority level
     * @throws EventRegistrationException if the method's first parameter is not a subclass of {@link Event}
     * @throws IllegalArgumentException if the method is not properly annotated with {@link Handler}
     */
    @Contract("_ -> new")
    public static @NotNull HandlerInfo of(@NotNull Method method) {
        HandlerPriority level = method.getAnnotation(Handler.class).level();
        Class<?> type = method.getParameterTypes()[0];

        if (!Event.class.isAssignableFrom(type))
            throw new EventRegistrationException(
                    "Handler method parameter must be a subclass of Event. Method: " +
                            method.getName() + " in " + method.getDeclaringClass().getSimpleName()
            );

        //noinspection unchecked
        return new HandlerInfo((Class<? extends Event>) type, level);
    }

    /**
     * Validates whether this handler can be properly registered based on monitorability constraints.
     * <p>
     * Monitor-level handlers ({@link HandlerPriority#isMonitor()}) can only be registered
     * for events that implement the {@link Monitorable} interface. This method checks this
     * constraint and returns {@code false} if a monitor handler is attempting to register
     * for a non-monitorable event.
     * </p>
     *
     * @return {@code true} if this handler violates monitorability constraints (i.e., is a monitor
     *         handler for a non-monitorable event), {@code false} otherwise
     *
     * @author LeyCM
     * @since 1.1.2
     */
    public boolean isMonitorable() {
        return level().isMonitor() && !Monitorable.class.isAssignableFrom(type());
    }

}

