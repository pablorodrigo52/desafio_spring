package br.com.mercadolivre.socialmeli.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.socialmeli.exception.CommonStatus;
import br.com.mercadolivre.socialmeli.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    

    private UserService service;

    @Autowired
    public UsersController (UserService service){
        this.service = service;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> sign (@PathVariable Long userId, @PathVariable Long userIdToFollow){
        CommonStatus status = service.sign(userId, userIdToFollow);
        if (status==CommonStatus.OK){
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (status==CommonStatus.USER_NOT_EXISTS){
            return new ResponseEntity<>("USER DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("CANNOT FOLLOW", HttpStatus.BAD_REQUEST);
        }
    }



}
