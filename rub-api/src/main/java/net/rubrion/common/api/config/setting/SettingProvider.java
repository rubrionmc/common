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

public class SettingProvider {

    private static final Map<String, ConfigReader> PROVIDERS = new HashMap<>();
    private static final Map<String, Map<String, Object>> CACHE = new HashMap<>();

    static {
        PROVIDERS.put("json", new JsonReader());
        PROVIDERS.put("yml", new YamlReader());
        PROVIDERS.put("yaml", new YamlReader());
        PROVIDERS.put("toml", new TomlReader());
    }

    public static synchronized Object read(@NotNull String file, @NotNull String key) {
        String ext = getExtension(file);
        ConfigReader provider = PROVIDERS.get(ext);
        if (provider == null) throw new IllegalArgumentException("No provider for file type: " + ext);

        Map<String, Object> data = CACHE.computeIfAbsent(file, f -> provider.load(new File(f)));
        return data.get(key);
    }

    public static synchronized void reload(@NotNull String file) {
        String ext = getExtension(file);
        ConfigReader provider = PROVIDERS.get(ext);
        if (provider != null) {
            CACHE.put(file, provider.load(new File(file)));
        }
    }

    public static synchronized void reloadAll() {
        for (String file : CACHE.keySet()) {
            reload(file);
        }
    }

    private static @NotNull String getExtension(@NotNull String file) {
        int dot = file.lastIndexOf('.');
        return (dot == -1) ? "" : file.substring(dot + 1).toLowerCase();
    }
}
