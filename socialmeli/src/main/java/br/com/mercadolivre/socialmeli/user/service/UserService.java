package br.com.mercadolivre.socialmeli.user.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.socialmeli.user.dto.SimpleUserDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowedDTO;
import br.com.mercadolivre.socialmeli.user.dto.UserFollowersDTO;
import br.com.mercadolivre.socialmeli.user.entities.User;
import br.com.mercadolivre.socialmeli.user.enums.CommonStatus;
import br.com.mercadolivre.socialmeli.user.enums.OrderBy;
import br.com.mercadolivre.socialmeli.user.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    private Comparator<String> comparatorFollowers = Comparator.naturalOrder();

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
            int followersCount = repository.getFollowers(userId).size();
            return new UserDTO(user.getUuid(), user.getName(), followersCount);
        }
        return null;
    }

    public UserFollowersDTO followersList(Long userId, String order) {
        User user = repository.findById(userId);
        if (user!=null){
            List<User> followers = repository.getFollowers(userId);
            List<SimpleUserDTO> followersDTOs;
            followersDTOs = followers
                .stream()
                .map(SimpleUserDTO::convert)
                .collect(Collectors.toList());

            if (order!=null){ // SE HOUVE UMA ORDENACAO QUE FOI PASSADA POR PARAMETRO NA CHAMADA DA API, EXECUTE
                if (order.equalsIgnoreCase(OrderBy.NAME_ASC.toString())){
                    comparatorFollowers = Comparator.naturalOrder();
                } else if (order.equalsIgnoreCase(OrderBy.NAME_DESC.toString())){
                    comparatorFollowers = Comparator.reverseOrder();
                }
            }

            followersDTOs = followersDTOs
                .stream()
                .sorted( (f1,f2) -> comparatorFollowers.compare(f1.getName(), f2.getName()))
                .collect(Collectors.toList());
            
            return new UserFollowersDTO(user.getUuid(), user.getName(), followersDTOs);
        }
        return null;
    }

    public UserFollowedDTO followingList(Long userId, String order) {
        User user = repository.findById(userId);
        if (user != null){
            List<User> following = repository.getFollowing(user.getFollowing());
            List<SimpleUserDTO> followingDTOs;
            followingDTOs = following
                .stream()
                .map(SimpleUserDTO::convert)
                .collect(Collectors.toList());

            if (order!=null){ // SE HOUVE UMA ORDENACAO QUE FOI PASSADA POR PARAMETRO NA CHAMADA DA API, EXECUTE
                if (order.equalsIgnoreCase(OrderBy.NAME_ASC.toString())){
                    comparatorFollowers = Comparator.naturalOrder();
                } else if (order.equalsIgnoreCase(OrderBy.NAME_DESC.toString())){
                    comparatorFollowers = Comparator.reverseOrder();
                }
            }

            followingDTOs = followingDTOs
                .stream()
                .sorted( (f1,f2) -> comparatorFollowers.compare(f1.getName(), f2.getName()))
                .collect(Collectors.toList());

            return new UserFollowedDTO(user.getUuid(), user.getName(), followingDTOs);
        }
        return null;
    }
}
