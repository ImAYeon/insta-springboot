package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();
    List<PostDto> getPostMyPostById(Integer id);
}
