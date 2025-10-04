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

/**
 * A type-safe configuration setting that represents a single configuration value
 * from a configuration file. Supports default values that are automatically saved
 * to the file if the key doesn't exist.
 *
 * @param <T> the type of the configuration value
 *
 * @see SettingProvider
 */
@SuppressWarnings("unchecked")
public class Setting<T> {
    private final String file;
    private final String key;
    private final T defaultValue;
    private final boolean hasDefault;

    /**
     * Constructs a new Setting instance without a default value.
     *
     * @param file the path to the configuration file (must not be null)
     * @param key the key identifying the configuration value (must not be null)
     * @throws IllegalArgumentException if no provider is available for the file type
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public Setting(String file, String key) {
        this.file = file;
        this.key = key;
        this.defaultValue = null;
        this.hasDefault = false;
        SettingProvider.ensureFileExists(file);
    }

    /**
     * Constructs a new Setting instance with a default value.
     * If the key doesn't exist in the configuration file, the default value
     * will be saved to the file automatically.
     *
     * @param file the path to the configuration file (must not be null)
     * @param key the key identifying the configuration value (must not be null)
     * @param defaultValue the default value to use if the key doesn't exist
     * @throws IllegalArgumentException if no provider is available for the file type
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public Setting(String file, String key, T defaultValue) {
        this.file = file;
        this.key = key;
        this.defaultValue = defaultValue;
        this.hasDefault = true;
        SettingProvider.ensureFileExists(file);
        SettingProvider.ensureKeyExists(file, key, defaultValue);
    }

    /**
     * Retrieves the current value of this configuration setting.
     * If a default value was provided and the key doesn't exist,
     * returns the default value.
     *
     * @return the configuration value of type T, or the default value if key doesn't exist
     * @throws ClassCastException if the actual value cannot be cast to type T
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public T get() {
        Object value = SettingProvider.read(file, key);
        if (value == null && hasDefault) {
            return defaultValue;
        }
        return (T) value;
    }

    /**
     * Sets a new value for this configuration setting and saves it to the file.
     *
     * @param value the new value to set
     * @throws RuntimeException if the configuration file cannot be written
     */
    public void set(T value) {
        SettingProvider.write(file, key, value);
    }

    /**
     * Reloads the configuration file associated with this setting.
     * This will refresh the cached values from disk.
     *
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public void reload() {
        SettingProvider.reload(file);
    }

    /**
     * Returns the default value for this setting.
     *
     * @return the default value, or null if no default was specified
     */
    public T getDefault() {
        return defaultValue;
    }

    /**
     * Checks if this setting has a default value.
     *
     * @return true if a default value was provided, false otherwise
     */
    public boolean hasDefault() {
        return hasDefault;
    }
}