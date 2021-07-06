package br.com.mercadolivre.socialmeli.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.socialmeli.dto.UserDTO;
import br.com.mercadolivre.socialmeli.entities.User;
import br.com.mercadolivre.socialmeli.exception.CommonStatus;
import br.com.mercadolivre.socialmeli.repository.UserRepository;

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
            if (!user.getFollowing().contains(userIdToFollow)){/* REGRA DE NEGÓCIO: NÃO É PERMITIDO SEGUIR A MESMA PESSOA DUAS VEZES */
                user.getFollowing().add(userIdToFollow);
                repository.saveReplacement(user);
                return CommonStatus.OK;
            } else {
                return CommonStatus.FOLLOW_NOT_VALID;
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
}
