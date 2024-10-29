package com.unicauca.smart_consumption.infrastructure.modules.user.api;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.domain.user.ports.in.IUserService;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.UserDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.UserMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/review/user")
@CrossOrigin(origins = "*" )
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "User APIs", description = "User web APIs")
public class UserWebApi {
    private final IUserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDto<UserDto>> getUserById(@PathVariable String entityId) {
        ResponseDto<User> userResponse = userService.findUserById(entityId);
        UserDto userDto = userMapper.toTarget(userResponse.getData());
        ResponseDto<UserDto> userDtoResponse = new ResponseDto<>(userResponse.getStatus(), userResponse.getMessage(), userDto);
        return userDtoResponse.of();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
        ResponseDto<List<User>> userResponse = userService.findAllUsers();
        return new ResponseDto<>(
                userResponse.getStatus(),
                userResponse.getMessage(),
                userResponse.getData().stream().map(userMapper::toTarget).toList()
        ).of();
    }

}
