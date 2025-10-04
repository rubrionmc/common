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

public class YamlReader implements ConfigReader {

    @Override
    public Map<String, Object> load(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            return new Yaml().load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read YAML: " + file, e);
        }
    }
}

