package br.com.mercadolivre.socialmeli.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * User
 */
public class User {

    private Long uuid;
    private String name;
    private List<Long> following; // QUEM EU ESTOU SEGUINDO

    public User() {
    }

    public User(long uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.following = new ArrayList<>();
    }

    public User(long uuid, String name, List<Long> following) {
        this.uuid = uuid;
        this.name = name;
        this.following = following;
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

}