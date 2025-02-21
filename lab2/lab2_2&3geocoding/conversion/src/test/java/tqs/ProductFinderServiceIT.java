package tqs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductFinderServiceIT {
    private ISimpleHttpClient httpClient;
    private ProductFinderService productFinderService;

    @BeforeEach
    public void setUp() {
        httpClient = new SimpleHttpClient();
        productFinderService = new ProductFinderService(httpClient);
    }

    @Test
    public void testFindProductDetails_ValidId() throws IOException {
        Optional<Product> product = productFinderService.findProductDetails(3);

        assertEquals(3, product.get().getId());
        assertEquals("Mens Cotton Jacket", product.get().getTitle());
    }

    @Test
    public void testFindProductDetails_InvalidId() throws IOException {
        Optional<Product> product = productFinderService.findProductDetails(300);

        assertFalse(product.isPresent());
    }
}
