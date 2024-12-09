package com.unicauca.smart_consumption.domain.user.ports.in;

import com.unicauca.smart_consumption.domain.user.User;

public interface IUserListener {
    void receiveCreatedUser(User user);
    void receiveUpdatedUser(User user);
}
