package br.com.mercadolivre.socialmeli.dto.Post;

import java.util.ArrayList;
import java.util.List;



public class PostsByFollowedDTO {

    private Long userId;
    List<SimplePostDTO> posts;

    public PostsByFollowedDTO() {
        this.posts = new ArrayList<>();
    }

    public PostsByFollowedDTO(Long userId, List<SimplePostDTO> posts) {
        this.userId = userId;
        this.posts = posts;
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
