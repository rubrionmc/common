package net.rubrion.common.api.id;

import org.jetbrains.annotations.NotNull;

/**
 * An {@link Identifier} implementation that wraps a long value.
 * This class provides type-safe long integer identification and extends
 * {@link Number} for numeric operations.
 *
 * @author LeyCM
 * @version 1.0.0
 * @since 1.0.0
 * @see Identifier
 * @see Number
 */
public class LongId extends Number implements Identifier<Long> {
    private final long value;

    /**
     * Constructs a new LongId with the specified long value.
     *
     * @param value the long value to use as identifier
     */
    public LongId(long value) {
        this.value = value;
    }

    /**
     * Returns the original long value that this identifier represents.
     *
     * @return the long value wrapped by this identifier
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
     */
    @Override
    public int compareTo(@NotNull Long o) {
        return Long.compare(value, o);
    }


    /**
     * Returns the value of this IntegerId as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code int}
     */
    @Override
    public int intValue() {
        return Math.toIntExact(value);
    }

    /**
     * Returns the value of this IntegerId as a {@code long}.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code long}
     */
    @Override
    public long longValue() {
        return value;
    }

    /**
     * Returns the value of this IntegerId as a {@code float}.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code float}
     */
    @Override
    public float floatValue() {
        return value;
    }

    /**
     * Returns the value of this IntegerId as a {@code double}.
     *
     * @return the numeric value represented by this object after conversion
     *         to type {@code double}
     */
    @Override
    public double doubleValue() {
        return value;
    }

    /**
     * Compares this IntegerId with the specified object for equality.
     * Returns true only if the specified object is also an IntegerId
     * and has the same integer value.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IntegerId integerId = (IntegerId) obj;
        return value == integerId.intValue();
    }

    /**
     * Returns a hash code value for this IntegerId.
     *
     * @return a hash code value based on the integer value
     */
    @Override
    public int hashCode() {
        return Integer.hashCode((int) value);
    }

    /**
     * Returns a string representation of this IntegerId.
     *
     * @return a string representation of the integer value
     */
    @Override
    public String toString() {
        return Integer.toString((int) value);
    }
}