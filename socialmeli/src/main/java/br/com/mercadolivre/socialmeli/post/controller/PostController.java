package br.com.mercadolivre.socialmeli.post.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.socialmeli.post.dto.PostDTO;
import br.com.mercadolivre.socialmeli.post.dto.PostsByFollowedDTO;
import br.com.mercadolivre.socialmeli.post.dto.PromoPostDTO;
import br.com.mercadolivre.socialmeli.post.services.PostService;
import br.com.mercadolivre.socialmeli.user.exception.UserNotExistsException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController (PostService service){
        this.service = service;
    }

    @PostMapping("/newpost")
    public ResponseEntity <PostDTO> newPost(@Valid @RequestBody PostDTO post){
        PostDTO postDto = service.newPost(post);
        if (postDto!=null) {
            return new ResponseEntity<>(service.newPost(post), HttpStatus.OK);
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<PromoPostDTO> newPost (@Valid @RequestBody PromoPostDTO post){
        PromoPostDTO postDto = service.newPost(post);
        if (postDto!=null) {
            return new ResponseEntity<>(service.newPost(post), HttpStatus.OK);
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity <PostsByFollowedDTO> postListByUserFollow(@PathVariable Long userId, @RequestParam(defaultValue = "") String order){
        PostsByFollowedDTO postListByUserFollow = service.postListByUserFollow(userId, order);
        if (postListByUserFollow!=null){
            return new ResponseEntity<>(postListByUserFollow, HttpStatus.OK);
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<PostsByFollowedDTO> promoPostsListByUser(@PathVariable Long userId){
        PostsByFollowedDTO listPromoPosts = service.promoPostsListByUser(userId);
        if (listPromoPosts!=null){
            return new ResponseEntity<>(service.promoPostsListByUser(userId), HttpStatus.OK);
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<String> countPromo (@PathVariable Long userId){
        PromoPostDTO promoPost = service.countPromo(userId);
        if (promoPost!=null){
            return new ResponseEntity<>(promoPost.toString(), HttpStatus.OK);            
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }
}
