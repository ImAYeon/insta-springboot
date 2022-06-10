package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.service.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@TokenRequired
@Slf4j
public class PostController {

    @Autowired
    PostServiceImpl postService;
    @Autowired
    SecurityService securityService;
    @Autowired
    PostDto postDto;

    @GetMapping("/")
    public List<PostDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/my")
    public List<SelectPostJoinUserDto> getMyPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostsByUserId(postDto);
    }

    @GetMapping("/{id}")
    public SelectPostJoinUserDto getPostsById(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        return postService.getPostById(postDto);
    }

    @DeleteMapping("/{id}")
    public Integer deleteMyPost(@PathVariable String id){ //post의 id값 얻어오기
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(securityService.getIdAtToken());
        return postService.deletePostByUserIdAndId(postDto);
    }

    @GetMapping("/other")
    public List<SelectPostJoinUserDto> getOtherPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.findPostsByNotUserId(postDto);
    }

    @PutMapping("/{id}")
    public Integer updateMyPost(
            @RequestBody PostDto postDto,
            @PathVariable String id
    ){
        postDto.setUserId(securityService.getIdAtToken());
        postDto.setId(Integer.valueOf(id));

        log.info(postDto.toString());
        return postService.updateMyPost(postDto);
    }

    @PostMapping("/")
    public Integer postPost(@RequestBody PostDto postDto){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.postPost(postDto);

    }


//
//    @PutMapping("/")
//    @TokenRequired
//    public ResponseEntity<?> updatePostById(){
//
//    }

}
