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

/**
 * Utility class for mapping configuration data to Java objects and vice versa.
 * Uses reflection and {@link ItemKey} annotations to map fields to configuration keys.
 *
 * <p>This mapper supports automatic type conversion for common primitive types
 * and their wrapper classes.</p>
 *
 * @see ItemKey
 * @see Item
 */
public class ItemMapper {

    /**
     * Maps configuration data to an instance of the specified class.
     * Fields annotated with {@link ItemKey} will be populated from the data map.
     *
     * @param <T> the type of object to create
     * @param clazz the class to instantiate and populate
     * @param data the configuration data map
     * @return a new instance of clazz with fields populated from the data map
     * @throws RuntimeException if instantiation fails or field access is denied
     */
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

    /**
     * Converts an object to a configuration data map.
     * Fields annotated with {@link ItemKey} will be included in the output map.
     *
     * @param obj the object to convert to a map
     * @return a map containing the object's annotated field values
     * @throws RuntimeException if field access is denied
     */
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

    /**
     * Converts a value to the target type.
     * Supports automatic conversion between Number types and common primitives.
     *
     * @param target the target type to convert to
     * @param value the value to convert
     * @return the converted value
     */
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

