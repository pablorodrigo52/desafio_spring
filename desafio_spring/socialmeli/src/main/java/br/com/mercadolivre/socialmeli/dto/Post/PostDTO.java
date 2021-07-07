package br.com.mercadolivre.socialmeli.dto.Post;

import java.math.BigDecimal;
import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.mercadolivre.socialmeli.entities.Post;

public class PostDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long id_post;
    
    @NotNull
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",
            lenient = OptBoolean.FALSE, locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date date;
    
    @NotNull
    private ProductDTO detail;

    @NotNull
    private int category;
    
    @NotNull
    private BigDecimal price;

    public PostDTO() {
    }

    public PostDTO(Post post){
        this.userId = post.getUserId();
        this.id_post = post.getId_post();
        this.date = post.getDate();
        this.detail = ProductDTO.convert(post.getDetail());
        this.category = post.getCategory();
        this.price = post.getPrice();
    }

    public PostDTO(Long userId, Long id_post, Date date, ProductDTO detail, int category, BigDecimal price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId_post() {
        return this.id_post;
    }

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductDTO getDetail() {
        return this.detail;
    }

    public void setDetail(ProductDTO detail) {
        this.detail = detail;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static PostDTO convert (Post post){
        return new PostDTO(post);
    }
    
    public static Post convert (PostDTO postDTO){
        return new Post(postDTO.getUserId(), postDTO.getId_post(), postDTO.getDate(), ProductDTO.convert(postDTO.getDetail()), postDTO.getCategory(), postDTO.getPrice());
    }
}
