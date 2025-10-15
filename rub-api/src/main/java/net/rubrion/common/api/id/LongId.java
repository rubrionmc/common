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
 * An {@link Identifier} implementation that wraps a long value.
 * This class provides type-safe long integer identification and extends
 * {@link Number} for numeric operations.
 *
 * @author LeyCM
 * @since 1.0.0
 * @see Identifier
 * @see Number
 * @see Identifiable
 */
public class LongId extends Number implements Identifier<Long> {
    private final long value;

    /**
     * Constructs a new LongId with the specified long value.
     *
     * @param value the long value to use as identifier
     *
     * @author LeyCM
     * @since 1.0.0
     */
    public LongId(long value) {
        this.value = value;
    }

    /**
     * Returns the original long value that this identifier represents.
     *
     * @return the long value wrapped by this identifier
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Identifier#original()
     */
    @Override
    public Long original() {
        return value;
    }

    /**
     * Compares this LongId with the specified Long for order.
     *
     * @param o the non-null Long to be compared
     * @return a negative integer, zero, or a positive integer as this
     *         identifier's value is less than, equal to, or greater than
     *         the specified long
     * @throws NullPointerException if the specified Long is null
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(@NotNull Long o) {
        return Long.compare(value, o);
    }

    /**
     * Returns the value of this LongId as an {@code int}.
     * Note: This may result in loss of precision for large long values.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code int}
     * @throws ArithmeticException if the long value exceeds int range
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Number#intValue()
     * @see Math#toIntExact(long)
     */
    @Override
    public int intValue() {
        return Math.toIntExact(value);
    }

    /**
     * Returns the value of this LongId as a {@code long}.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code long}
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Number#longValue()
     */
    @Override
    public long longValue() {
        return value;
    }

    /**
     * Returns the value of this LongId as a {@code float}.
     * Note: This may result in loss of precision for large long values.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code float}
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Number#floatValue()
     */
    @Override
    public float floatValue() {
        return value;
    }

    /**
     * Returns the value of this LongId as a {@code double}.
     * Note: This may result in loss of precision for very large long values.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code double}
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Number#doubleValue()
     */
    @Override
    public double doubleValue() {
        return value;
    }

    /**
     * Compares this LongId with the specified object for equality.
     * Returns true only if the specified object is also a LongId
     * and has the same long value.
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
        if (obj == null || getClass() != obj.getClass()) return false;
        LongId longId = (LongId) obj;
        return value == longId.value;
    }

    /**
     * Returns a hash code value for this LongId.
     *
     * @return a hash code value based on the long value
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    /**
     * Returns a string representation of this LongId.
     *
     * @return a string representation of the long value
     *
     * @author LeyCM
     * @since 1.0.0
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return Long.toString(value);
    }
}