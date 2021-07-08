package br.com.mercadolivre.socialmeli.post.repository;

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

import br.com.mercadolivre.socialmeli.post.entities.Post;

@Repository
public class PostRepository {

    @Autowired
    private ObjectMapper mapper;

    private static File FILE = new File("post.json");

    

    public boolean save(Post post){
        List<Post> posts = getPosts();
        posts.add(post);
        return writePosts(posts);
    }

    public List<Post> getPosts(Long userId){
        return getPosts()
        .stream()
        .filter(post -> post.getUserId().equals(userId))
        .collect(Collectors.toList());
    }

    private List<Post> getPosts(){
        List<Post> posts = new ArrayList<>();
        try{
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Post>> typeReference = new TypeReference<List<Post>>() {};
            posts = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return posts;
    }

    private boolean writePosts(List<Post> posts){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, posts);
            out.close();
            return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }
}
