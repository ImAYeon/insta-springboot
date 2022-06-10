package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.repository.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostMapper postMapper;

    @Override
    public List<PostDto> getPosts() {
        return postMapper.getPosts();
    }

    @Override
    public List<SelectPostJoinUserDto> getPostsByUserId(PostDto postDto) {
        return postMapper.findPostsByUserId(postDto);
    }

    @Override
    public Integer deletePostByUserIdAndId(PostDto postDto) {
        return postMapper.deletePostByUserIdAndId(postDto);
    }

    @Override
    public List<SelectPostJoinUserDto> getPostsByNotUserId(PostDto postDto) {
        return postMapper.findPostsByNotUserId(postDto);
    }

    @Override
    public Integer updateMyPost(PostDto postDto) {
        return postMapper.updatePostByUserIdAndId(postDto);
    }

    @Override
    public Integer postPost(PostDto postDto) {
        return postMapper.postPost(postDto);
    }


}
