package br.com.mercadolivre.socialmeli.entities;

import java.util.List;

public class Seller extends User{

    private List<User> followers;

    public Seller() {
    }

    public Seller(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
    
}
