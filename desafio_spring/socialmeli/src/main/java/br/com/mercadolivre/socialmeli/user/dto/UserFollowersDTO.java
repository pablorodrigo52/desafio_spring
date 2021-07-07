package br.com.mercadolivre.socialmeli.user.dto;

import java.util.List;

public class UserFollowersDTO {
 
    private Long uuid;
    private String name;
    private List<SimpleUserDTO> followers;


    public UserFollowersDTO(Long uuid, String name, List<SimpleUserDTO> followers) {
        this.uuid = uuid;
        this.name = name;
        this.followers = followers;
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

    public List<SimpleUserDTO> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<SimpleUserDTO> followers) {
        this.followers = followers;
    }
    
    
}
