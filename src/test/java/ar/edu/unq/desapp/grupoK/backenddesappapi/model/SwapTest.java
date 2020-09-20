package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class SwapTest {
    @Test
    public void gettersValidInNewSwaps() {
        Product product = mock (Product.class);
        Product product1 = mock (Product.class);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);

        Swap swap = new Swap(products);
        assertEquals(swap.getProducts().size(), 2);
    }
}
