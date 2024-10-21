package com.unicauca.smart_consumption.application.service.user;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.domain.user.ports.in.IUserListener;
import com.unicauca.smart_consumption.domain.user.ports.out.IUserRepository;
import com.unicauca.smart_consumption.infrastructure.config.RabbitMQConfig;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserListenerImpl implements IUserListener{

    private final IUserRepository userRepository;

    @Override
    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void receiveCreatedUser(User user) {
        System.out.println("User received: " + user);
        userRepository.createUser(user);
    }
}
