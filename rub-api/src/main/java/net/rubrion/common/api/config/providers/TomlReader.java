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
package net.rubrion.common.api.config.providers;

import com.moandjiezana.toml.Toml;
import net.rubrion.common.api.config.ConfigReader;

import java.io.File;
import java.util.Map;

/**
 * Configuration file reader implementation for TOML files.
 * Uses Toml4J library for parsing TOML format.
 *
 * @see ConfigReader
 */
public class TomlReader implements ConfigReader {

    /**
     * Loads and parses a TOML configuration file.
     *
     * @param file the TOML file to load (must not be null)
     * @return a map containing all configuration key-value pairs from the file
     * @throws RuntimeException if the file cannot be read or contains invalid TOML
     */
    @Override
    public Map<String, Object> load(File file) {
        return new Toml().read(file).toMap();
    }
}

