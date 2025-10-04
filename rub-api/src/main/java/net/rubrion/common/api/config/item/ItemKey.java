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
package net.rubrion.common.api.config.item;

import java.lang.annotation.*;

/**
 * Annotation to mark a field as a configuration item and specify its key name.
 * Used by {@link ItemMapper} to map configuration data to object fields.
 *
 * <p>Example usage:
 * <pre>
 * public class DatabaseConfig implements Item {
 *     {@literal @}ItemKey("database.host")
 *     private String host;
 *
 *     {@literal @}ItemKey("database.port")
 *     private int port;
 * }
 * </pre>
 *
 * @see ItemMapper
 * @see Item
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ItemKey {

    /**
     * The configuration key that maps to this field.
     *
     * @return the configuration key name
     */
    String value();
}

