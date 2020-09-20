package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void gettersValidInNewProduct() {
        Product product = new Product("Bici", 3, 4000);
        assertEquals(product.getName(), "Bici");
        assertEquals(product.getStock(), 3);
        assertEquals(product.getPointForProduct(), 4000);
    }
}
