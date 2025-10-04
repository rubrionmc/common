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
 * from a configuration file.
 *
 * @param <T> the type of the configuration value
 * @param file the path to the configuration file
 * @param key the key identifying the configuration value within the file
 *
 * @see SettingProvider
 */
@SuppressWarnings("unchecked")
public record Setting<T>(String file, String key) {

    /**
     * Constructs a new Setting instance and pre-loads the configuration value.
     *
     * @param file the path to the configuration file (must not be null)
     * @param key the key identifying the configuration value (must not be null)
     * @throws IllegalArgumentException if no provider is available for the file type
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public Setting(String file, String key) {
        this.file = file;
        this.key = key;
        SettingProvider.read(file, key);
    }

    /**
     * Retrieves the current value of this configuration setting.
     * The value is cast to the parameterized type T.
     *
     * @return the configuration value of type T
     * @throws ClassCastException if the actual value cannot be cast to type T
     * @throws RuntimeException if the configuration file cannot be read or parsed
     */
    public T get() {
        return (T) SettingProvider.read(file, key);
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

}
