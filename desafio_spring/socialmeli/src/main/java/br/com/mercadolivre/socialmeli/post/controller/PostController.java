package br.com.mercadolivre.socialmeli.post.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.socialmeli.post.dto.PostDTO;
import br.com.mercadolivre.socialmeli.post.dto.PostsByFollowedDTO;
import br.com.mercadolivre.socialmeli.post.services.PostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/products")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController (PostService service){
        this.service = service;
    }

    @PostMapping("/newpost")
    public ResponseEntity <?> newPost(@Valid @RequestBody PostDTO post){
        return new ResponseEntity<>(service.newPost(post), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity <?> postListByUserFollow(@PathVariable Long userId){
        PostsByFollowedDTO postListByUserFollow = service.postListByUserFollow(userId);
        if (postListByUserFollow!=null){
            return new ResponseEntity<>(postListByUserFollow, HttpStatus.OK);
        }
        return new ResponseEntity<>("USER DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }
}
