package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
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
    public List<PostDto> getPostMyPostById(Integer id) {
        return postMapper.getPostMyPostById(id);
    }


}
