package br.com.mercadolivre.socialmeli.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.socialmeli.user.dto.SimpleUserDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowedDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowersDTO;
import br.com.mercadolivre.socialmeli.user.entities.User;
import br.com.mercadolivre.socialmeli.user.exception.CommonStatus;
import br.com.mercadolivre.socialmeli.user.repository.UserRepository;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService (UserRepository repository){
        this.repository = repository;
    }

    public CommonStatus sign (Long userId, long userIdToFollow){
        User user = repository.findById(userId);
        User userToFollow = repository.findById(userIdToFollow);

        if (user!=null && userToFollow!=null){ 
            if (!user.getFollowing().contains(userIdToFollow) && !userId.equals(userIdToFollow)){/* REGRA DE NEGÓCIO: NÃO É PERMITIDO SEGUIR A MESMA PESSOA DUAS VEZES e NÃO POSSO SEGUIR EU MESMO */
                user.getFollowing().add(userIdToFollow);
                repository.saveReplacement(user);
                return CommonStatus.OK;
            } else {
                return CommonStatus.FOLLOW_NOT_VALID;
            }
        }
        return CommonStatus.USER_NOT_EXISTS;
    }

    public CommonStatus unsign(Long userId, Long userIdToUnfollow) {
        User user = repository.findById(userId);
        User userToFollow = repository.findById(userIdToUnfollow);

        if (user!=null && userToFollow!=null){ 
            if (user.getFollowing().contains(userIdToUnfollow)){/* REGRA DE NEGÓCIO: NÃO É PERMITIDO DEIXAR DE SEGUIR UMA PESSOA QUE EU NÃO SIGO */
                user.getFollowing().remove(userIdToUnfollow);
                repository.saveReplacement(user);
                return CommonStatus.OK;
            } else {
                return CommonStatus.UNFOLLOW_NOT_VALID;
            }
        }
        return CommonStatus.USER_NOT_EXISTS;
    }

    public UserDTO countFollows(Long userId){
        User user = repository.findById(userId);

        if (user!=null){
            return UserDTO.convert(user);
        }
        return null;
    }

    public UserFollowersDTO followersList(Long userId) {
        User user = repository.findById(userId);
        if (user!=null){
            List<User> followers = repository.getFollowers(userId);
            List<SimpleUserDTO> followersDTOs;
            followersDTOs = followers.stream().map(SimpleUserDTO::convert).collect(Collectors.toList());
            return new UserFollowersDTO(user.getUuid(), user.getName(), followersDTOs);
        }
        return null;
    }

    public UserFollowedDTO followingList(Long userId) {
        User user = repository.findById(userId);
        if (user != null){
            List<User> following = repository.getFollowing(user.getFollowing());
            List<SimpleUserDTO> followingDTOs;
            followingDTOs = following.stream().map(SimpleUserDTO::convert).collect(Collectors.toList());
            return new UserFollowedDTO(user.getUuid(), user.getName(), followingDTOs);
        }
        return null;
    }
}
