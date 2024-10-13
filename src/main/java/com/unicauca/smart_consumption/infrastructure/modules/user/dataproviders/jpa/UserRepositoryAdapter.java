package com.unicauca.smart_consumption.infrastructure.modules.user.dataproviders.jpa;

import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.domain.user.ports.out.IUserRepository;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.UserJPAMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserRepositoryAdapter implements IUserRepository {

    private final UserJPARepository userJPARepository;
    private final UserJPAMapper userJPAMapper;

    @Override
    public Optional<User> findUserById(String id) {
        Optional<UserJPAEntity> userJPA=userJPARepository.findById(id);
        User mapped=userJPAMapper.toDomain(userJPA.orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id))));
        return Optional.of(mapped);
    }
}
