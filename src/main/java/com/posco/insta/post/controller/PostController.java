package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public List<PostDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/my")
    @TokenRequired
    public List<PostDto> getPostMyPost(){
        return postService.getPostMyPostById(securityService.getIdByToken());

    }

    

}
