package br.com.mercadolivre.socialmeli.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.mercadolivre.socialmeli.entities.User;

@Repository
public class UserRepository {

    @Autowired
    private ObjectMapper mapper;

    private static File FILE = new File("user.json");
    
    public boolean saveReplacement(User user){
        List<User> users = getUsers();
        User userAux = users.stream().filter(usr -> usr.getUuid().equals(user.getUuid())).findFirst().orElse(null);
        if (userAux!=null){
            users.set(users.indexOf(userAux), user);
            return writeUsers(users);
        }
        return false;
    }

    public boolean save(User user){
        List<User> users = getUsers();
        users.add(user);
        return writeUsers(users);
    }


    public User findById(Long userId){
        return getUsers()
        .stream()
        .filter(usr -> usr.getUuid().equals(userId)).findFirst().orElse(null);
    }

    public List<User> getFollowers(Long userId){
        return getUsers().stream().filter(usr -> usr.getFollowing().contains(userId)).collect(Collectors.toList());
    }

    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try{
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
            users = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return users;
    }

    private boolean writeUsers(List<User> users){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, users);
            out.close();
            return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }
}
