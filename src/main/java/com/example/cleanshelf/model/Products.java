package com.example.cleanshelf.model;


import jakarta.persistence.*;


//The entity is used to define the type of data we're working with
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private String description;
    private String image;
    private Integer price;
    private String unit;
    private Integer number;

    //non argument constructor
    public Products() {

    }

    //All argument constructor

    public Products(Integer id, String name, String category, String description, String image, Integer price, String unit, Integer number
    ) {
        this.id = id;
        this.category = category;
        this.image = image;
        this.name = name;
        //this.number = number;
        this.unit = unit;
        this.description = description;
        this.price = price;

    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
