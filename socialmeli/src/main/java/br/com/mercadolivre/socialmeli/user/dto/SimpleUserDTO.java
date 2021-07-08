package br.com.mercadolivre.socialmeli.user.dto;

import br.com.mercadolivre.socialmeli.user.entities.User;

public class SimpleUserDTO {

    private Long uuid;
    private String name;


    public SimpleUserDTO (User user){
        this.uuid = user.getUuid();
        this.name = user.getName();
    }

    public SimpleUserDTO(Long uuid, String name) {
        this.uuid = uuid;
        this.name = name;
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

    public static SimpleUserDTO convert (User user){
        return new SimpleUserDTO(user);
    }
}
