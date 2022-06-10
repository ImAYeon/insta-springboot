package com.posco.insta.user.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> postUser(@RequestBody UserDto userDto){
        HttpStatus httpStatus = userService.insertUser(userDto)==1
                ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping("/")
    @TokenRequired
    public Integer deleteUserById(){
        return userService.deleteUserById(securityService.getIdAtToken());
    }

    @PutMapping("/")
    @TokenRequired
    @Operation(description = "정보 업데이트")
    public Integer updateUserById(@RequestBody UserDto userDto){
        userDto.setId(securityService.getIdAtToken());
        return userService.updateUserById(userDto);
    }


    @PostMapping("/login")
    @Operation(description = "로그인")
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

        UserDto userDto = new UserDto();
        userDto.setId(securityService.getIdAtToken());
        return userService.findUserById(userDto);
    }

    @TokenRequired
    @GetMapping("/check")
    public Boolean check(){
        return true;
    }
}
