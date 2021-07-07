package br.com.mercadolivre.socialmeli.services;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.socialmeli.dto.Post.PostsByFollowedDTO;
import br.com.mercadolivre.socialmeli.dto.Post.SimplePostDTO;
import br.com.mercadolivre.socialmeli.dto.Post.PostDTO;
import br.com.mercadolivre.socialmeli.entities.Post;
import br.com.mercadolivre.socialmeli.entities.User;
import br.com.mercadolivre.socialmeli.helper.DateUtils;
import br.com.mercadolivre.socialmeli.repository.PostRepository;
import br.com.mercadolivre.socialmeli.repository.UserRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostDTO newPost(PostDTO post){
        if(postRepository.save(PostDTO.convert(post))){
            return post;
        }
        return null;
    }

    public PostsByFollowedDTO postListByUserFollow(Long userId) {
        User user = userRepository.findById(userId);
        PostsByFollowedDTO posts = new PostsByFollowedDTO();
        if (user!=null){
            posts.setUserId(userId);
            for (Long id : user.getFollowing()) { // TODOS OS VENDEDORES QUE userId ESTÁ SEGUINDO
                List<Post> postsSeller = postRepository.getPosts(id);
                postsSeller = postsSeller.stream().filter(post -> post.getDate().after(DateUtils.now())).collect(Collectors.toList());
                postsSeller.sort(Collections.reverseOrder());
                for (Post post : postsSeller) {
                    posts.addPost(SimplePostDTO.convert(post));
                }
            }
            return posts;
        }
        return null;
    }
}
