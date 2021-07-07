package br.com.mercadolivre.socialmeli.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.socialmeli.dto.Post.PostDTO;
import br.com.mercadolivre.socialmeli.services.PostService;

@RestController
@RequestMapping("/products")
public class PostController {

    private PostService service;

    @Autowired
    public PostController (PostService service){
        this.service = service;
    }

    @PostMapping("/newpost")
    public ResponseEntity <?> newPost(@Valid @RequestBody PostDTO post){
        return new ResponseEntity<>(this.service.newPost(post), HttpStatus.OK);
    } 

}
