package com.unicauca.smart_consumption.domain.user.ports.out;

import com.unicauca.smart_consumption.domain.user.User;
import java.util.Optional;

public interface IUserRepository {

    /**
     * Finds a user in the system by its ID.
     *
     * @param id The ID of the {@link User} to be retrieved.
     * @return An {@link Optional} containing the found {@link User} if it exists, or {@link Optional#empty()} if not found.
     */
    Optional<User> findUserById(String id);
}
