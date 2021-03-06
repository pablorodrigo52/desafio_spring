package br.com.mercadolivre.socialmeli.user.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.socialmeli.user.dto.UserDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowedDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowersDTO;
import br.com.mercadolivre.socialmeli.user.enums.CommonStatus;
import br.com.mercadolivre.socialmeli.user.exception.CantFollowException;
import br.com.mercadolivre.socialmeli.user.exception.UserNotExistsException;
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
    public ResponseEntity<HttpStatus> follow (@PathVariable Long userId, @PathVariable Long userIdToFollow){
        CommonStatus status = service.sign(userId, userIdToFollow);
        if (status==CommonStatus.OK){
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (status==CommonStatus.USER_NOT_EXISTS){
            throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
        } else {
            throw new CantFollowException(CantFollowException.CANT_FOLLOW_MSG);
        }
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<HttpStatus> unfollow(@PathVariable Long userId, @PathVariable Long userIdToUnfollow){
        CommonStatus status = service.unsign(userId, userIdToUnfollow);
        if (status==CommonStatus.OK){
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (status==CommonStatus.USER_NOT_EXISTS){
            throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
        } else {
            throw new CantFollowException(CantFollowException.CANT_FOLLOW_MSG);
        }
    }    

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<String> countFollows (@PathVariable Long userId){
        UserDTO userDTO = service.countFollows(userId);
        if (userDTO!=null){
            return new ResponseEntity<>(userDTO.followersCount(), HttpStatus.OK);
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowersDTO> followersList (@PathVariable Long userId,  @RequestParam(defaultValue = "") String order){
        UserFollowersDTO followers = service.followersList(userId, order);
        if (followers!=null){
            return new ResponseEntity<>(followers, HttpStatus.OK);
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowedDTO> followingList (@PathVariable Long userId, @RequestParam(defaultValue = "") String order){
        UserFollowedDTO followed = service.followingList(userId, order);
        if (followed!=null){
            return new ResponseEntity<>(followed, HttpStatus.OK);
        }
        throw new UserNotExistsException(UserNotExistsException.USER_NOT_FOUND_MSG);
    }

}
