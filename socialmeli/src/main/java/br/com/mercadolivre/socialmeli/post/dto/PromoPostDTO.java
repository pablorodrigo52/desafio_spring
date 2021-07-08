package br.com.mercadolivre.socialmeli.post.dto;

import java.math.BigDecimal;
import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.mercadolivre.socialmeli.post.entities.Post;

public class PromoPostDTO {

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

    @NotNull
    private boolean hasPromo;

    @NotNull 
    private BigDecimal discount;

    private int promoCounter;
    private String userName;

    public PromoPostDTO() {
    }

    public PromoPostDTO(Post post){
        this.userId = post.getUserId();
        this.id_post = post.getId_post();
        this.date = post.getDate();
        this.detail = ProductDTO.convert(post.getDetail());
        this.category = post.getCategory();
        this.price = post.getPrice();
        this.hasPromo = post.getHasPromo();
        this.discount = post.getDiscount();
    }

    public PromoPostDTO(Long userId, Long id_post, Date date, ProductDTO detail, int category, BigDecimal price, boolean hasPromo, BigDecimal discount) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public PromoPostDTO (Long userId, String userName, int promoCounter){
        this.userId = userId;
        this.userName = userName;
        this.promoCounter = promoCounter;
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

    public static PromoPostDTO convert (Post post){
        return new PromoPostDTO(post);
    }
    
    public static Post convert (PromoPostDTO promoPostDTO){
        return new Post(
            promoPostDTO.getUserId(),
            promoPostDTO.getId_post(),
            promoPostDTO.getDate(),
            ProductDTO.convert(promoPostDTO.getDetail()),
            promoPostDTO.getCategory(),
            promoPostDTO.getPrice(),
            promoPostDTO.getHasPromo(),
            promoPostDTO.getDiscount()
        );
    }

    @Override
    public String toString(){
        return "{"+
            "\"userId\":"+ this.userId +","+
            "\"userName\":\""+ this.userName +"\","+
            "\"promoproducts_count\":"+ this.promoCounter+
        "}";
    }
}
