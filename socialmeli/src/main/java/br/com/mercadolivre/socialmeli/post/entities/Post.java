package br.com.mercadolivre.socialmeli.post.entities;

import java.math.BigDecimal;
import java.sql.Date;

public class Post implements Comparable<Post>{

    private Long userId, id_post;
    private Date date;
    private Product detail;
    private int category;
    private BigDecimal price;
    private boolean hasPromo = false;
    private BigDecimal discount;

    public Post() {
    }

    public Post(Long userId, Long id_post, Date date, Product detail, int category, BigDecimal price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public Post(Long userId, Long id_post, Date date, Product detail, int category, BigDecimal price, boolean hasPromo, BigDecimal discount) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
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

    public Product getDetail() {
        return this.detail;
    }

    public void setDetail(Product detail) {
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

    @Override
    public int compareTo(Post o) {
        return this.getDate().compareTo(o.getDate());
    }

}
