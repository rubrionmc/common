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
package net.rubrion.common.api.id;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a namespaced identifier (like "minecraft:diamond").
 * <p>
 * Ensures the ID has a valid namespace and key.
 */
public class NamespacedId implements CharSequence, Identifier<String> {
    private final String namespace;
    private final String key;

    /**
     * Creates a new {@link NamespacedId} from a string like "namespace:key".
     *
     * @param id the namespaced string
     * @throws IllegalArgumentException if id is null or invalid
     */
    public NamespacedId(@NotNull String id) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        String[] parts = id.split(":", 2);
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new IllegalArgumentException("Invalid namespaced ID: " + id);
        }
        this.namespace = parts[0];
        this.key = parts[1];
    }

    /**
     * Returns the namespace part (before the colon).
     *
     * @return namespace
     */
    public @NotNull String namespace() {
        return namespace;
    }

    /**
     * Returns the key part (after the colon).
     *
     * @return key
     */
    public @NotNull String key() {
        return key;
    }

    /**
     * Returns the full string representation ("namespace:key").
     */
    @Override
    public @NotNull String original() {
        return namespace + ":" + key;
    }

    @Override
    public int length() {
        return original().length();
    }

    @Override
    public char charAt(int index) {
        return original().charAt(index);
    }

    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return original().subSequence(start, end);
    }

    @Override
    public @NotNull String toString() {
        return original();
    }

    @Override
    public int compareTo(@NotNull String o) {
        return original().compareTo(o);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NamespacedId other)) return false;
        return namespace.equals(other.namespace) && key.equals(other.key);
    }

    @Override
    public int hashCode() {
        return 31 * namespace.hashCode() + key.hashCode();
    }
}
