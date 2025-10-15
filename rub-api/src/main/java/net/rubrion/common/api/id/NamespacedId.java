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
 * Ensures the ID has a valid namespace and key format.
 * This is commonly used in resource location systems.
 *
 * @author LeyCM
 * @since 1.0.0
 * @see Identifier
 * @see CharSequence
 */
public class NamespacedId implements CharSequence, Identifier<String> {
    private final String namespace;
    private final String key;

    /**
     * Creates a new {@link NamespacedId} from a string like "namespace:key".
     *
     * @param id the namespaced string
     * @throws IllegalArgumentException if id is null or invalid format
     *
     * @author LeyCM
     * @since 1.0.0
     */
    public NamespacedId(@NotNull String id) {
        //noinspection ConstantValue
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
     * @return namespace the namespace string
     *
     * @author LeyCM
     * @since 1.0.0
     */
    public @NotNull String namespace() {
        return namespace;
    }

    /**
     * Returns the key part (after the colon).
     *
     * @return key the key string
     *
     * @author LeyCM
     * @since 1.0.0
     */
    public @NotNull String key() {
        return key;
    }

    /**
     * Returns the full string representation ("namespace:key").
     *
     * @return the full namespaced identifier string
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Identifier#original()
     */
    @Override
    public @NotNull String original() {
        return namespace + ":" + key;
    }

    /**
     * Returns the length of the namespaced identifier string.
     *
     * @return the length of the string representation
     *
     * @author LeyCM
     * @since 1.0.0
     * @see CharSequence#length()
     */
    @Override
    public int length() {
        return original().length();
    }

    /**
     * Returns the character at the specified index.
     *
     * @param index the index of the character
     * @return the character at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     *
     * @author LeyCM
     * @since 1.0.0
     * @see CharSequence#charAt(int)
     */
    @Override
    public char charAt(int index) {
        return original().charAt(index);
    }

    /**
     * Returns a subsequence of the namespaced identifier string.
     *
     * @param start the start index, inclusive
     * @param end   the end index, exclusive
     * @return a subsequence of the string
     * @throws IndexOutOfBoundsException if start or end are invalid
     *
     * @author LeyCM
     * @since 1.0.0
     * @see CharSequence#subSequence(int, int)
     */
    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return original().subSequence(start, end);
    }

    /**
     * Returns the string representation of this namespaced identifier.
     *
     * @return the full namespaced identifier string
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Object#toString()
     */
    @Override
    public @NotNull String toString() {
        return original();
    }

    /**
     * Compares this namespaced identifier to another string lexicographically.
     *
     * @param o the string to compare to
     * @return a negative integer, zero, or a positive integer as this identifier
     *         is less than, equal to, or greater than the specified string
     * @throws NullPointerException if the specified string is null
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(@NotNull String o) {
        return original().compareTo(o);
    }

    /**
     * Compares this NamespacedId with the specified object for equality.
     * Two NamespacedIds are equal if they have the same namespace and key.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NamespacedId other)) return false;
        return namespace.equals(other.namespace) && key.equals(other.key);
    }

    /**
     * Returns a hash code value for this NamespacedId.
     *
     * @return a hash code value based on namespace and key
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 * namespace.hashCode() + key.hashCode();
    }
}