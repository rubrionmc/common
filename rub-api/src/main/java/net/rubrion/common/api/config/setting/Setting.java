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

@SuppressWarnings("unchecked")
public record Setting<T>(String file, String key) {

    public Setting(String file, String key) {
        this.file = file;
        this.key = key;
        SettingProvider.read(file, key);
    }

    public T get() {
        return (T) SettingProvider.read(file, key);
    }

    public void reload() {
        SettingProvider.reload(file);
    }

}
