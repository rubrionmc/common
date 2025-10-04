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
import com.moandjiezana.toml.TomlWriter;
import net.rubrion.common.api.config.ConfigReader;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ConfigReader implementation for TOML files using toml4j.
 */
public class TomlReader implements ConfigReader {

    private static final TomlWriter TOML_WRITER = new TomlWriter();

    @Override
    public @NotNull Map<String, Object> load(@NotNull File file) {
        try {
            Toml toml = new Toml().read(file);
            Map<String, Object> data = toml.toMap();
            return data != null ? data : new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load TOML file: " + file.getPath(), e);
        }
    }

    @Override
    public void save(@NotNull File file, @NotNull Map<String, Object> data) {
        try (Writer writer = new FileWriter(file)) {
            TOML_WRITER.write(data, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save TOML file: " + file.getPath(), e);
        }
    }
}