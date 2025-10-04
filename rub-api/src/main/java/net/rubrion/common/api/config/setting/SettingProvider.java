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
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides centralized access to configuration settings with format detection and caching.
 * Supports multiple configuration file formats through pluggable ConfigReader implementations.
 * Automatically creates files and saves default values when they don't exist.
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
     * Ensures that a configuration file exists. If it doesn't exist,
     * creates it with an empty configuration.
     *
     * @param file the path to the configuration file (must not be null)
     * @throws RuntimeException if the file cannot be created
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static synchronized void ensureFileExists(@NotNull String file) {
        File f = new File(file);
        if (!f.exists()) {
            File parent = f.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            String ext = getExtension(file);
            ConfigReader provider = PROVIDERS.get(ext);
            if (provider == null) {
                throw new IllegalArgumentException("No provider for file type: " + ext);
            }

            try {
                f.createNewFile();
                provider.save(f, new HashMap<>());
                CACHE.put(file, new HashMap<>());
            } catch (IOException e) {
                throw new RuntimeException("Failed to create configuration file: " + file, e);
            }
        }
    }

    /**
     * Ensures that a key exists in the configuration file. If it doesn't exist,
     * saves the default value to the file.
     *
     * @param file the path to the configuration file (must not be null)
     * @param key the key identifying the configuration value (must not be null)
     * @param defaultValue the default value to save if the key doesn't exist
     * @throws RuntimeException if the configuration file cannot be written
     */
    public static synchronized void ensureKeyExists(@NotNull String file, @NotNull String key, @Nullable Object defaultValue) {
        Object currentValue = read(file, key);
        if (currentValue == null && defaultValue != null) {
            write(file, key, defaultValue);
        }
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
        if (provider == null) {
            throw new IllegalArgumentException("No provider for file type: " + ext);
        }

        Map<String, Object> data = CACHE.computeIfAbsent(file, f -> {
            File configFile = new File(f);
            if (!configFile.exists()) {
                return new HashMap<>();
            }
            return provider.load(configFile);
        });

        return data.get(key);
    }

    /**
     * Writes a configuration value to the specified file and key.
     * The value is saved both to the cache and to the file on disk.
     *
     * @param file the path to the configuration file (must not be null)
     * @param key the key identifying the configuration value (must not be null)
     * @param value the value to write
     * @throws IllegalArgumentException if no provider is available for the file type
     * @throws RuntimeException if the configuration file cannot be written
     */
    public static synchronized void write(@NotNull String file, @NotNull String key, @Nullable Object value) {
        String ext = getExtension(file);
        ConfigReader provider = PROVIDERS.get(ext);
        if (provider == null) {
            throw new IllegalArgumentException("No provider for file type: " + ext);
        }

        ensureFileExists(file);

        Map<String, Object> data = CACHE.computeIfAbsent(file, f -> {
            File configFile = new File(f);
            if (!configFile.exists()) {
                return new HashMap<>();
            }
            return provider.load(configFile);
        });

        data.put(key, value);
        provider.save(new File(file), data);
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
            File configFile = new File(file);
            if (configFile.exists()) {
                CACHE.put(file, provider.load(configFile));
            }
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
     * Clears the cache for a specific configuration file.
     *
     * @param file the path to the configuration file (must not be null)
     */
    public static synchronized void clearCache(@NotNull String file) {
        CACHE.remove(file);
    }

    /**
     * Clears the entire configuration cache.
     */
    public static synchronized void clearAllCache() {
        CACHE.clear();
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