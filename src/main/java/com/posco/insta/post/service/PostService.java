package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();
    List<SelectPostJoinUserDto> getPostsByUserId(PostDto postDto);
    Integer deletePostByUserIdAndId(PostDto postDto);
    List<SelectPostJoinUserDto> findPostsByNotUserId(PostDto postDto);

    Integer updateMyPost(PostDto postDto);

    Integer postPost(PostDto postDto);

    SelectPostJoinUserDto getPostById(PostDto postDto);
}
