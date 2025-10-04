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
package net.rubrion.common.api.config.item;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ItemMapper {

    public static <T> @NotNull T load(Class<T> clazz, Map<String, Object> data) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                ItemKey keyAnn = field.getAnnotation(ItemKey.class);
                if (keyAnn != null) {
                    String key = keyAnn.value();
                    Object value = data.get(key);
                    if (value != null) {
                        field.setAccessible(true);
                        field.set(instance, convert(field.getType(), value));
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map settings to " + clazz.getName(), e);
        }
    }

    public static @NotNull Map<String, Object> unload(Object obj) {
        Map<String, Object> data = new HashMap<>();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                ItemKey keyAnn = field.getAnnotation(ItemKey.class);
                if (keyAnn != null) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    data.put(keyAnn.value(), value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to map object to settings", e);
        }
        return data;
    }

    private static Object convert(@NotNull Class<?> target, @NotNull Object value) {
        if (target.isAssignableFrom(value.getClass())) return value;
        if (target == int.class || target == Integer.class) return ((Number) value).intValue();
        if (target == long.class || target == Long.class) return ((Number) value).longValue();
        if (target == double.class || target == Double.class) return ((Number) value).doubleValue();
        if (target == float.class || target == Float.class) return ((Number) value).floatValue();
        if (target == boolean.class || target == Boolean.class) return Boolean.parseBoolean(value.toString());
        if (target == String.class) return value.toString();
        return value;
    }
}

