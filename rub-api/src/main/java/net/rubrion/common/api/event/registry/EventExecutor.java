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
import net.rubrion.common.api.event.able.Monitorable;
import net.rubrion.common.api.event.exception.NotMonitorableException;
import net.rubrion.common.api.event.handler.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * Executes event handlers for different handler levels.
 * This class manages handlers by priority level and ensures proper event handling order.
 *
 * @author LeyCM
 * @since 1.1.2
 * @see Event
 * @see Handler
 * @see HandlerPriority
 */
public final class EventExecutor {
    private final Map<HandlerPriority, Set<Consumer<Event>>> handlers = new EnumMap<>(HandlerPriority.class);

    /**
     * Fires an event to all registered handlers in order of their priority levels.
     *
     * <p>For monitor levels, a copy of the event is created if the event implements {@link Monitorable}.
     * The execution order is: {@link HandlerPriority#EARLY} → {@link HandlerPriority#NORMAL} →
     * {@link HandlerPriority#LATE} → {@link HandlerPriority#MONITOR}.
     *
     * @param event the event to fire to all registered handlers
     * @author LeyCM
     * @since 1.1.2
     * @see Monitorable#copy()
     */
    public void fire(Event event) {
        for (Map.Entry<HandlerPriority, Set<Consumer<Event>>> entry : handlers.entrySet()) {
            HandlerPriority level = entry.getKey();
            Set<Consumer<Event>> set = entry.getValue();

            Event toFire = prepareEventForHandling(event, level);

            set.forEach(h -> h.accept(toFire));
        }
    }

    /**
     * Registers a handler for a specific handler level.
     *
     * @param info the handler annotation containing event type and level information, must not be {@code null}
     * @param handler the consumer that will process the event, must not be {@code null}
     * @throws NotMonitorableException if level requires monitoring but event class doesn't implement Monitorable
     * @throws IllegalArgumentException if any parameter is null
     * @author LeyCM
     * @since 1.1.2
     */
    public void register(@NotNull HandlerInfo info, @NotNull Consumer<Event> handler) {
        if(info.isMonitorable())
            throw new NotMonitorableException("Try to register an Handler on Monitor level " +
                    " for the not Monitorable event " + info.type().getSimpleName());

        handlers.computeIfAbsent(info.level(), unused -> new HashSet<>())
                .add(handler);
    }

    /**
     * Prepares an event for handling based on the handler level.
     * For monitor levels, creates a copy of monitorable events.
     *
     * @param event the original event
     * @param level the handler level
     * @return the event to be handled (original or copy)
     */
    private Event prepareEventForHandling(Event event, @NotNull HandlerPriority level) {
        if (level.isMonitor() && event instanceof Monitorable<?> m) return m.copy();

        return event;
    }

}