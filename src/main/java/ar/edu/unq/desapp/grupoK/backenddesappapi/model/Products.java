package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class Products {
    Integer point ;
    String name;
    Integer stock;

    Products(Integer point,String name , Integer stock){
        this.point = point;
        this.name = name;
        this.stock = stock;
    }
}
