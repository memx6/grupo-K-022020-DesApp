package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.ArrayList;

public class Swap {
    ArrayList<Product> products;

    Swap(ArrayList<Product> products){
        this.products = products;
    }

    public ArrayList<Product> getProducts(){ return this.products; }
}
