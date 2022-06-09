package com.posco.insta.user.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public List<UserDto> getUser(){
        return userService.findUser();
    }

    @GetMapping("/{id}")
    @TokenRequired
    public UserDto getUserById(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return userService.findUserById(userDto);
    }

    @PostMapping("/")
    public Integer postUser(@RequestBody UserDto userDto){
        return userService.insertUser(userDto);
    }

    @DeleteMapping("/")
    @TokenRequired
    public Integer deleteUserById(){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization");

        // 토큰에서 id값 빼오는 거
        String id = securityService.getSubject(tokenBearer);

        return userService.deleteUserById(Integer.valueOf(id));
    }

    @PutMapping("/")
    @TokenRequired
    public Integer updateUserById(@RequestBody UserDto userDto){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization");

        // 토큰에서 id값 빼오는 거
        String id = securityService.getSubject(tokenBearer);

        userDto.setId(Integer.valueOf(id));
        return userService.updateUserById(userDto);
    }


    @PostMapping("/login")
    public Map loginUser(@RequestBody UserDto userDto){
        UserDto loginUser = userService.serviceLogin(userDto);
        String token = securityService.createToken(
                loginUser.getId().toString());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("name", loginUser.getName());
        map.put("img", loginUser.getImg());

        return map;
    }

//    @GetMapping("/token")
//    @TokenRequired
//    public String getToken(){
//        // SecurityAspect와 똑같은 코드 -> 그냥 테스트하려고
//        ServletRequestAttributes requestAttributes =
//                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//
//        String tokenBearer = request.getHeader("Authorization");
//
//        String subject = securityService.getSubject(tokenBearer);
//        return subject;
//    }

    @GetMapping("/me")
    @TokenRequired
    public UserDto getUserByMe(){
        log.info("cont");
        // header에서 빼오는 거
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization");

        // 토큰에서 id값 빼오는 거
        String id = securityService.getSubject(tokenBearer);

        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return userService.findUserById(userDto);
    }
}
