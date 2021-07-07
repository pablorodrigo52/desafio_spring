package br.com.mercadolivre.socialmeli.dto.User;

import java.util.ArrayList;
import java.util.List;

import br.com.mercadolivre.socialmeli.entities.User;

/**
 * User
 */
public class UserDTO {

    private Long uuid;
    private String name;
    private List<Long> following; // QUEM EU ESTOU SEGUINDO

    public UserDTO() {
    }

    public UserDTO(long uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.following = new ArrayList<>();
    }

    public UserDTO(long uuid, String name, List<Long> following) {
        this.uuid = uuid;
        this.name = name;
        this.following = following;
    }

    public UserDTO(User user){
        this.uuid = user.getUuid();
        this.name = user.getName();
        this.following = user.getFollowing();
    }

    public List<Long> getFollowing() {
        return this.following;
    }

    public void setFollowing(List<Long> following) {
        this.following = following;
    }

    public Long getUuid() {
        return this.uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String followersCount() {
        return "{" +
            " \"userId\"=" + getUuid() + "" +
            ", \"userName\"=\"" + getName() + "\"" +
            ", \"followers_count\"=" + getFollowing().size() + "" +
            "}";
    }

    public static User convert (UserDTO userDTO){
        return new User(userDTO);
    }

    public static UserDTO convert (User user){
        return new UserDTO(user);
    }
}