/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hkhat
 */
public class ProductDTO {
    private String id;
    private String name;
    private String brand;
    private String tags;
    private String description;
    private String category;
    private double mrp;

    public ProductDTO(String id, String name, String brand, String tags, String description, String category, double mrp) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.tags = tags;
        this.description = description;
        this.category = category;
        this.mrp = mrp;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    // toString method

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", tags='" + tags + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", mrp=" + mrp +
                '}';
    }
}
