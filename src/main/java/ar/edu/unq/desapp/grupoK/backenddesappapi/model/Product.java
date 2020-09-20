package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class Product {
    String name;
    Integer stock;
    Integer points;

    Product(String name, Integer stock, Integer points){
        this.name = name;
        this.stock = stock;
        this.points = points;
    }

    public String getName() { return  this.name; }

    public Integer getStock() { return this.stock; }

    public Integer getPointForProduct() { return this.points; }
}
