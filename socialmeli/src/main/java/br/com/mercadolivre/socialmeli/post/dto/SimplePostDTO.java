package br.com.mercadolivre.socialmeli.post.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.mercadolivre.socialmeli.post.entities.Post;

public class SimplePostDTO {

    private Long id_post;
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",
            lenient = OptBoolean.FALSE, locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date date;
    private ProductDTO detail;
    private int category;
    private BigDecimal price;
    private boolean hasPromo;
    private BigDecimal discount;


    public SimplePostDTO() {
    }

    public SimplePostDTO(Post post){
        this.id_post = post.getId_post();
        this.date = post.getDate();
        this.detail = ProductDTO.convert(post.getDetail());
        this.category = post.getCategory();
        this.price = post.getPrice();
        if (post.getHasPromo()){
            this.hasPromo = post.getHasPromo();
            this.discount = post.getDiscount();
        }
    }

    public SimplePostDTO(Long id_post, Date date, ProductDTO detail, int category, BigDecimal price) {
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }


    public SimplePostDTO(Long id_post, Date date, ProductDTO detail, int category, BigDecimal price, boolean hasPromo, BigDecimal discount) {
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
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

    public boolean isHasPromo() {
        return this.hasPromo;
    }

    public boolean getHasPromo() {
        return this.hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public static SimplePostDTO convert (Post post){
        return new SimplePostDTO(post);
    }
}
