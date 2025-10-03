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
 * @version 1.0.0
 * @since 1.0.0
 * @see Identifier
 * @see UUID
 */
public class UniqueId implements Identifier<UUID> {
    private final UUID uuid;

    /**
     * Constructs a new UniqueId with the specified UUID.
     *
     * @param uuid the UUID to use as identifier, should not be null
     */
    public UniqueId(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Compares this UniqueId with the specified UUID for order.
     * The comparison is delegated to the underlying UUID's comparison logic.
     *
     * @param o the non-null UUID to be compared
     * @return a negative integer, zero, or a positive integer as this
     *         identifier's UUID is less than, equal to, or greater than
     *         the specified UUID
     * @throws NullPointerException if the specified UUID is null
     */
    @Override
    public int compareTo(@NotNull UUID o) {
        return uuid.compareTo(o);
    }

    /**
     * Returns the original UUID that this identifier represents.
     *
     * @return the UUID wrapped by this identifier
     */
    @Override
    public UUID original() {
        return uuid;
    }
}
