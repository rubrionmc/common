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

import java.util.UUID;

/**
 * An {@link Identifier} implementation that wraps a {@link UUID}.
 * This class provides type-safe universal unique identification
 * suitable for distributed systems.
 *
 * @author LeyCM
 * @see Identifier
 * @see UUID
 * @see Identifiable
 * @since 1.0.0
 */
public record UniqueId(UUID uuid) implements Identifier<UUID> {
    /**
     * Constructs a new UniqueId with the specified UUID.
     *
     * @param uuid the UUID to use as identifier, should not be null
     * @author LeyCM
     * @since 1.0.0
     */
    public UniqueId {
    }

    /**
     * Compares this UniqueId with the specified UUID for order.
     * The comparison is delegated to the underlying UUID's comparison logic.
     *
     * @param o the non-null UUID to be compared
     * @return a negative integer, zero, or a positive integer as this
     * identifier's UUID is less than, equal to, or greater than
     * the specified UUID
     * @throws NullPointerException if the specified UUID is null
     * @author LeyCM
     * @see Comparable#compareTo(Object)
     * @since 1.0.0
     */
    @Override
    public int compareTo(@NotNull UUID o) {
        return uuid.compareTo(o);
    }

    /**
     * Returns the original UUID that this identifier represents.
     *
     * @return the UUID wrapped by this identifier
     * @author LeyCM
     * @see Identifier#original()
     * @since 1.0.0
     */
    @Override
    public UUID original() {
        return uuid;
    }

    /**
     * Compares this UniqueId with the specified object for equality.
     * Returns true only if the specified object is also a UniqueId
     * and has the same UUID value.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     * @author LeyCM
     * @see Object#equals(Object)
     * @since 1.0.0
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UniqueId other)) return false;
        return uuid.equals(other.uuid);
    }

    /**
     * Returns a string representation of this UniqueId.
     *
     * @return a string representation of the UUID
     * @author LeyCM
     * @see Object#toString()
     * @since 1.0.0
     */
    @Override
    public String toString() {
        return uuid.toString();
    }
}