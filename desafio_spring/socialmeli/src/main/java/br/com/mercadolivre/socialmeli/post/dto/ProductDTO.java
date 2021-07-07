package br.com.mercadolivre.socialmeli.post.dto;


import javax.validation.constraints.NotNull;

import br.com.mercadolivre.socialmeli.post.entities.Product;

public class ProductDTO {

    @NotNull
    private Long product_id;
    
    @NotNull
    private String productName, type, brand, color, notes;

    public ProductDTO() {
    }

    public ProductDTO(Product product){
        this.product_id = product.getProduct_id();
        this.productName = product.getProduct_name();
        this.type = product.getType();
        this.brand = product.getBrand();
        this.color = product.getColor();
        this.notes = product.getNotes();
    }
    
    public ProductDTO(Long product_id, String productName, String type, String brand, String color, String notes) {
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }

    public Long getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static ProductDTO convert(Product detail) {
        return new ProductDTO(detail);
    }

    public static Product convert (ProductDTO productDto){
        return new Product(productDto.getProduct_id(), productDto.getProductName(), productDto.getType(), productDto.getBrand(), productDto.getColor(), productDto.getNotes());
    }
    
}
