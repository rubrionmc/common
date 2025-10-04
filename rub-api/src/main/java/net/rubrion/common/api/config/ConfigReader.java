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
package net.rubrion.common.api.config;

import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.Map;

/**
 * Interface for configuration file readers that can load and save configuration data.
 */
public interface ConfigReader {

    /**
     * Loads configuration data from a file.
     *
     * @param file the configuration file to load
     * @return a map containing the configuration data
     * @throws RuntimeException if the file cannot be read or parsed
     */
    @NotNull Map<String, Object> load(@NotNull File file);

    /**
     * Saves configuration data to a file.
     *
     * @param file the configuration file to save to
     * @param data the configuration data to save
     * @throws RuntimeException if the file cannot be written
     */
    void save(@NotNull File file, @NotNull Map<String, Object> data);
}