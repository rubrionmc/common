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
 * A generic identifier for objects that provides type-safe identification
 * and comparison capabilities. Implementations should be immutable and
 * properly implement {@code equals()} and {@code hashCode()}.
 *
 * @param <Ob> the type of the original object being identified
 *
 * @author LeyCM
 * @since 1.0.1
 * @see Comparable
 * @see Identifiable
 */
public interface Identifier<Ob> extends Comparable<Ob> {

    /**
     * Returns the original object that this identifier represents.
     * This is typically the underlying value that the identifier wraps.
     *
     * @return the original object, implementation specific whether null is allowed
     *
     * @author LeyCM
     * @since 1.0.1
     */
    Ob original();

    /**
     * Compares this identifier with another identifier for order based on their
     * original objects. The comparison is delegated to the original objects.
     *
     * @param identifier the non-null identifier to be compared
     * @return a negative integer, zero, or a positive integer as this identifier
     *         is less than, equal to, or greater than the specified identifier
     * @throws NullPointerException if the specified identifier is null
     * @throws ClassCastException if the original objects are not mutually comparable
     *
     * @author LeyCM
     * @since 1.0.1
     * @see #compareTo(Object)
     */
    default int compareTo(@NotNull Identifier<Ob> identifier) {
        return compareTo(identifier.original());
    }

    /**
     * Compares this identifier with the specified identifier for equality.
     * Two identifiers are considered equal if their original objects compare as equal.
     *
     * @param identifier the identifier to compare with for equality
     * @return {@code true} if this identifier is equal to the specified identifier,
     *         {@code false} otherwise
     *
     * @author LeyCM
     * @since 1.0.1
     * @see Object#equals(Object)
     */
    default boolean equals(Identifier<Ob> identifier) {
        return compareTo(identifier) == 0;
    }
}
