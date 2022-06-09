package com.posco.insta.user.service;

import com.posco.insta.user.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findUser();
    UserDto findUserById(UserDto userDto);

    Integer insertUser(UserDto userDto);

    Integer deleteUserById(Integer id);

    Integer updateUserById(UserDto userDto);

    UserDto serviceLogin(UserDto userDto);
}
