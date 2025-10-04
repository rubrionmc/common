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

import net.rubrion.common.api.config.ConfigReader;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


/**
 * Configuration file reader implementation for YAML files.
 * Uses SnakeYAML library for parsing YAML format.
 *
 * @see ConfigReader
 */
public class YamlReader implements ConfigReader {

    /**
     * Loads and parses a YAML configuration file.
     *
     * @param file the YAML file to load (must not be null)
     * @return a map containing all configuration key-value pairs from the file
     * @throws RuntimeException if the file cannot be read or contains invalid YAML
     */
    @Override
    public Map<String, Object> load(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            return new Yaml().load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read YAML: " + file, e);
        }
    }
}

