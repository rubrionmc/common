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

/**
 * Marker interface for classes that contain event handler methods.
 * Classes implementing this interface can have their methods annotated with {@link Handler}
 * to automatically register them as event handlers.
 *
 * @author LeyCM
 * @since 1.1.2
 * @see Handler
 * @see net.rubrion.common.api.event.registry.EventBus#register(HandlerList)
 */
public interface HandlerList { }
