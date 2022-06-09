package com.posco.insta.user.repository;

import com.posco.insta.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> getUser();
    UserDto getUserById(UserDto userDto);

    Integer postUser(UserDto userDto);

    Integer deleteUserById(Integer id);

    Integer updateUserById(UserDto userDto);

    UserDto getUserByUserIdAndPassword(UserDto userDto);
}
