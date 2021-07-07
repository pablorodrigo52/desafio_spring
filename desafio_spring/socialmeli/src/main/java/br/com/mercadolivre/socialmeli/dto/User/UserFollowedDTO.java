package br.com.mercadolivre.socialmeli.dto.User;

import java.util.List;

public class UserFollowedDTO {
 
    private Long uuid;
    private String name;
    private List<SimpleUserDTO> followed;


    public UserFollowedDTO(Long uuid, String name, List<SimpleUserDTO> followed) {
        this.uuid = uuid;
        this.name = name;
        this.followed = followed;
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

    public List<SimpleUserDTO> getFollowed() {
        return this.followed;
    }

    public void setFollowed(List<SimpleUserDTO> followed) {
        this.followed = followed;
    }
    
    
}
