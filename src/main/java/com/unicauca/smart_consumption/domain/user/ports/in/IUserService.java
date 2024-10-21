package com.unicauca.smart_consumption.domain.user.ports.in;

import java.util.List;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.user.User;

public interface IUserService {
    /**
     * Finds a user in the system by its ID.
     *
     * @param id The ID of the {@link User} to be retrieved.
     * @return A {@link ResponseDto} containing the found {@link User} if it exists, and an HTTP status code.
     */
    ResponseDto<User> findUserById(String id);

    /**
     * Retrieves a list of all users in the system.
     *
     * @return A {@link ResponseDto} containing the list of all {@link User} objects and an HTTP status code.
     */
    ResponseDto<List<User>> findAllUsers();
}
