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
 * Represents an identifier backed by a {@link String}.
 * Implements {@link CharSequence} to behave like a standard string
 * and {@link Identifier} for a typed ID abstraction.
 *
 * @author LeyCM
 * @see Identifier
 * @see CharSequence
 * @since 1.0.0
 */
public record StringId(String value) implements CharSequence, Identifier<String> {
    /**
     * Creates a new {@link StringId} with the given string value.
     *
     * @param value the string backing this identifier
     * @throws IllegalArgumentException if value is null
     * @author LeyCM
     * @since 1.0.0
     */
    @SuppressWarnings("ConstantValue")
    public StringId(@NotNull String value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        this.value = value;
    }

    /**
     * Returns the original string value of this identifier.
     *
     * @return the original string
     * @author LeyCM
     * @see Identifier#original()
     * @since 1.0.0
     */
    @Override
    public String original() {
        return value;
    }

    /**
     * Returns the length of the string identifier.
     *
     * @return the length of the string
     * @author LeyCM
     * @see CharSequence#length()
     * @since 1.0.0
     */
    @Override
    public int length() {
        return value.length();
    }

    /**
     * Returns the character at the specified index.
     *
     * @param index the index of the character
     * @return the character at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     * @author LeyCM
     * @see CharSequence#charAt(int)
     * @since 1.0.0
     */
    @Override
    public char charAt(int index) {
        return value.charAt(index);
    }

    /**
     * Returns a subsequence of the string identifier.
     *
     * @param start the start index, inclusive
     * @param end   the end index, exclusive
     * @return a subsequence of the string
     * @throws IndexOutOfBoundsException if start or end are invalid
     * @author LeyCM
     * @see CharSequence#subSequence(int, int)
     * @since 1.0.0
     */
    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return value.subSequence(start, end);
    }

    /**
     * Returns the string representation of this identifier.
     *
     * @return the string value
     * @author LeyCM
     * @see Object#toString()
     * @since 1.0.0
     */
    @Override
    public @NotNull String toString() {
        return value;
    }

    /**
     * Compares this identifier to another string lexicographically.
     *
     * @param o the string to compare to
     * @return a negative integer, zero, or a positive integer as this identifier
     * is less than, equal to, or greater than the specified string
     * @throws NullPointerException if the specified string is null
     * @author LeyCM
     * @see Comparable#compareTo(Object)
     * @since 1.0.0
     */
    @Override
    public int compareTo(@NotNull String o) {
        return value.compareTo(o);
    }

    /**
     * Checks equality based on the underlying string value.
     *
     * @param obj the object to compare
     * @return true if the other object is a StringId with the same value
     * @author LeyCM
     * @see Object#equals(Object)
     * @since 1.0.0
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StringId other)) return false;
        return value.equals(other.value);
    }

}
