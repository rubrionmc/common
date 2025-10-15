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
package net.rubrion.common.api.event.registry;

import net.rubrion.common.api.event.Event;
import net.rubrion.common.api.event.exception.ProcessHandlerException;
import net.rubrion.common.api.event.handler.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Central event bus for registering and firing events.
 * This class provides static methods to manage event handlers and dispatch events.
 *
 * @author LeyCM
 * @since 1.1.2
 * @see Event
 * @see HandlerList
 * @see EventExecutor
 */
public final class EventBus {
    private static final Map<Class<? extends Event>, EventExecutor> executors = new HashMap<>();

    /**
     * Fires an event to all registered handlers for that event type.
     *
     * @param event the event to fire, must not be {@code null}
     * @throws IllegalArgumentException if event is {@code null}
     * @author LeyCM
     * @since 1.1.2
     */
    public static void fire(@NotNull Event event) {
        if (!executors.containsKey(event.getClass())) return;
        executors.get(event.getClass()).fire(event);
    }

    /**
     * Registers all handler methods from a {@link HandlerList} implementation.
     * This method scans the class for methods annotated with {@link Handler} and registers them.
     *
     * @param list the handler list containing annotated handler methods, must not be {@code null}
     * @throws IllegalArgumentException if list is {@code null}
     * @throws ProcessHandlerException if a handler method cannot be invoked
     * @author LeyCM
     * @since 1.1.2
     * @see Handler
     * @see HandlerList
     */
    public static void register(@NotNull HandlerList list) {
        Arrays.stream(list.getClass().getDeclaredMethods())
                .forEach(m -> register(m, list));
    }

    /**
     * Registers a single method as an event handler if it meets the criteria.
     *
     * @param method the method to register, must not be {@code null}
     * @param ob the object instance that contains the method
     * @throws ProcessHandlerException if the handler method cannot be invoked during event processing
     */
    private static void register(@NotNull Method method, HandlerList ob) {
        if (!method.isAnnotationPresent(Handler.class)) return;
        if (method.getParameterCount() != 1) return;
        method.setAccessible(true);


        HandlerInfo info = HandlerInfo.of(method);

        executors.computeIfAbsent(info.type(),
                        unused -> new EventExecutor())
                .register(info, e -> {
                    try { method.invoke(ob, e);}
                    catch (IllegalAccessException | InvocationTargetException ex) {
                        throw new ProcessHandlerException("Fail to Process Handler", ex);
                    }
                });
    }

}
