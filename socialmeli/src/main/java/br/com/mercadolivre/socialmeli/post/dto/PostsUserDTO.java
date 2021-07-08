package br.com.mercadolivre.socialmeli.post.dto;

import java.util.ArrayList;
import java.util.List;



public class PostsUserDTO {

    private String userName;
    private Long userId;
    List<SimplePostDTO> posts;

    public PostsUserDTO() {
        this.posts = new ArrayList<>();
    }

    public PostsUserDTO(String userName, Long userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public PostsUserDTO(String userName, Long userId, List<SimplePostDTO> posts) {
        this.userName = userName;
        this.userId = userId;
        this.posts = posts;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SimplePostDTO> getPosts() {
        return this.posts;
    }

    public void addPost(SimplePostDTO post){
        this.posts.add(post);
    }

    public void setPosts(List<SimplePostDTO> posts) {
        this.posts = posts;
    }
}
