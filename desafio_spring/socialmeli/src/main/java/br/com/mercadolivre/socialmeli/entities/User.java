package br.com.mercadolivre.socialmeli.entities;

/**
 * User
 */
public class User {

    private String uuid;
    private String name;

    public User() {
    }

    public User(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}