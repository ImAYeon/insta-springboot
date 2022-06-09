package com.posco.insta.post.repository;

import com.posco.insta.post.model.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDto> getPosts();
    List<PostDto> getPostMyPostById(Integer id);
}
