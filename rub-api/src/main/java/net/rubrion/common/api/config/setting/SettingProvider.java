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
package net.rubrion.common.api.config.setting;

import net.rubrion.common.api.config.ConfigReader;
import net.rubrion.common.api.config.providers.*;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides centralized access to configuration settings with format detection and caching.
 * Supports multiple configuration file formats through pluggable ConfigReader implementations.
 *
 * <p>Supported file formats:
 * <ul>
 *   <li>JSON (.json)</li>
 *   <li>YAML (.yml, .yaml)</li>
 *   <li>TOML (.toml)</li>
 * </ul>
 *
 * <p>This class maintains a cache of loaded configuration files to improve performance.
 * All operations are thread-safe.</p>
 */
public class SettingProvider {

    private static final Map<String, ConfigReader> PROVIDERS = new HashMap<>();
    private static final Map<String, Map<String, Object>> CACHE = new HashMap<>();

    static {
        PROVIDERS.put("json", new JsonReader());
        PROVIDERS.put("yml", new YamlReader());
        PROVIDERS.put("yaml", new YamlReader());
        PROVIDERS.put("toml", new TomlReader());
    }

    /**
     * Reads a configuration value from the specified file and key.
     * The file format is automatically detected from the file extension.
     *
     * @param file the path to the configuration file (must not be null)
     * @param key the key identifying the configuration value (must not be null)
     * @return the configuration value, or null if the key does not exist
     * @throws IllegalArgumentException if no provider is available for the file type
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public static synchronized Object read(@NotNull String file, @NotNull String key) {
        String ext = getExtension(file);
        ConfigReader provider = PROVIDERS.get(ext);
        if (provider == null) throw new IllegalArgumentException("No provider for file type: " + ext);

        Map<String, Object> data = CACHE.computeIfAbsent(file, f -> provider.load(new File(f)));
        return data.get(key);
    }

    /**
     * Reloads a specific configuration file from disk and updates the cache.
     * This is useful when configuration files are modified at runtime.
     *
     * @param file the path to the configuration file to reload (must not be null)
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public static synchronized void reload(@NotNull String file) {
        String ext = getExtension(file);
        ConfigReader provider = PROVIDERS.get(ext);
        if (provider != null) {
            CACHE.put(file, provider.load(new File(file)));
        }
    }

    /**
     * Reloads all cached configuration files from disk.
     * This method will refresh all configuration values from their source files.
     *
     * @throws RuntimeException if any configuration file cannot be read or parsed
     */
    public static synchronized void reloadAll() {
        for (String file : CACHE.keySet()) {
            reload(file);
        }
    }

    /**
     * Extracts the file extension from a file path.
     *
     * @param file the file path (must not be null)
     * @return the file extension in lowercase, or empty string if no extension found
     */
    private static @NotNull String getExtension(@NotNull String file) {
        int dot = file.lastIndexOf('.');
        return (dot == -1) ? "" : file.substring(dot + 1).toLowerCase();
    }
}
