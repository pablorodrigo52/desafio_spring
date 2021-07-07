package br.com.mercadolivre.socialmeli.user.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.socialmeli.user.dto.UserDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowedDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowersDTO;
import br.com.mercadolivre.socialmeli.user.exception.CommonStatus;
import br.com.mercadolivre.socialmeli.user.service.UserService;


@RestController
@RequestMapping(value = "/users", produces = "application/json")
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

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> countFollows (@PathVariable Long userId){
        UserDTO userDTO = service.countFollows(userId);
        if (userDTO!=null){
            return new ResponseEntity<>(userDTO.followersCount(), HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> followersList (@PathVariable Long userId){
        UserFollowersDTO followers = service.followersList(userId);
        if (followers!=null){
            return new ResponseEntity<>(followers, HttpStatus.OK);
        }
        return new ResponseEntity<>("USER DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> followingList (@PathVariable Long userId){
        UserFollowedDTO followed = service.followingList(userId);
        if (followed!=null){
            return new ResponseEntity<>(followed, HttpStatus.OK);
        }
        return new ResponseEntity<>("USER DOES NOT EXISTS", HttpStatus.BAD_REQUEST);
    }

}