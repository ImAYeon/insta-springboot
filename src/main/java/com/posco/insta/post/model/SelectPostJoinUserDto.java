package com.posco.insta.post.model;

import lombok.Data;

@Data
public class SelectPostJoinUserDto {
    private Integer id;
    private Integer userId;
    private String img;
    private String content;
    private String userImg;
    private String userName;
}
