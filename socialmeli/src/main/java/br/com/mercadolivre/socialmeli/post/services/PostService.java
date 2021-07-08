package br.com.mercadolivre.socialmeli.post.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.socialmeli.post.dto.PostDTO;
import br.com.mercadolivre.socialmeli.post.dto.PostsByFollowedDTO;
import br.com.mercadolivre.socialmeli.post.dto.SimplePostDTO;
import br.com.mercadolivre.socialmeli.post.entities.Post;
import br.com.mercadolivre.socialmeli.post.helper.DateUtils;
import br.com.mercadolivre.socialmeli.post.repository.PostRepository;
import br.com.mercadolivre.socialmeli.user.entities.User;
import br.com.mercadolivre.socialmeli.user.enums.OrderBy;
import br.com.mercadolivre.socialmeli.user.repository.UserRepository;

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

    public PostsByFollowedDTO postListByUserFollow(Long userId, String order) {
        User user = userRepository.findById(userId);
        PostsByFollowedDTO posts = new PostsByFollowedDTO();
        if (user!=null){
            posts.setUserId(userId);
            for (Long id : user.getFollowing()) { // TODOS OS VENDEDORES QUE userId ESTÁ SEGUINDO
                List<Post> postsSeller = postRepository.getPosts(id);
                postsSeller = postsSeller
                    .stream()
                    .filter(post -> post.getDate().after(DateUtils.now()))
                    .collect(Collectors.toList());                
                
                if (order!=null){ // SE HOUVE UMA ORDENACAO QUE FOI PASSADA POR PARAMETRO NA CHAMADA DA API, EXECUTE
                    if (order.equalsIgnoreCase(OrderBy.DATE_ASC.toString())){
                        postsSeller.sort(Comparator.naturalOrder());                        
                    } else if (order.equalsIgnoreCase(OrderBy.DATE_DESC.toString())){
                        postsSeller.sort(Collections.reverseOrder());
                    }
                }
                
                for (Post post : postsSeller) {
                    posts.addPost(SimplePostDTO.convert(post));
                }
            }
            return posts;
        }
        return null;
    }
}
