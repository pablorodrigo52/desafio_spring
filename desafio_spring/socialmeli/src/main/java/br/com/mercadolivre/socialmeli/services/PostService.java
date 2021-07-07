package br.com.mercadolivre.socialmeli.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.socialmeli.dto.Post.PostDTO;
import br.com.mercadolivre.socialmeli.repository.PostRepository;

@Service
public class PostService {

    private PostRepository repository;
    
    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public PostDTO newPost(PostDTO post){
        if(repository.save(PostDTO.convert(post))){
            return post;
        }
        return null;
    }
}
