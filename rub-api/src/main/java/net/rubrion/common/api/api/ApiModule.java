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
package net.rubrion.common.api.api;

import net.rubrion.common.api.id.NamespacedId;
import org.slf4j.Logger;

public interface ApiModule {

    /**
     * Returns the unique identifier for this API module loader.
     * The {@link NamespacedId} is used to identify and distinguish between
     * different API modules within the framework.
     *
     * @return the unique {@link NamespacedId} identifying this module loader
     * @see NamespacedId
     */
    NamespacedId loader();

    /**
     * Returns the logger instance associated with this API module.
     * The logger should be used for all logging activities within the module
     * to ensure consistent logging behavior and proper log management.
     *
     * @return the {@link Logger} instance for this module
     * @see Logger
     */
    Logger logger();
}
