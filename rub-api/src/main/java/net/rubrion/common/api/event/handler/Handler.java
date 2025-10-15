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

import java.lang.annotation.*;

/**
 * Annotation to mark methods as event handlers.
 * Methods annotated with {@code @Handler} will be automatically registered as event handlers.
 *
 * @author LeyCM
 * @since 1.1.2
 * @see Event
 * @see HandlerPriority
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Handler {

    /**
     * The priority level of this handler.
     * Defaults to {@link HandlerPriority#NORMAL}.
     *
     * @return the handler level
     * @see HandlerPriority
     */
    HandlerPriority level() default HandlerPriority.NORMAL;

}
