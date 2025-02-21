package tqs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class ProductFinderServiceTest {
    @Mock
    private ISimpleHttpClient httpClient;

    private ProductFinderService productFinderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productFinderService = new ProductFinderService(httpClient);
    }

    @Test
    public void testFindProductDetails_ValidId() throws IOException {
        String jsonResponse = "{" +
                    "\"id\":3," +
                    "\"title\":\"Mens Cotton Jacket\"," +
                    "\"price\":55.99," +
                    "\"description\":\"Great jacket\"," +
                    "\"category\":\"men's clothing\"," +
                    "\"image\":\"image_url\"" +
                "}";
        when(httpClient.doHttpGet("https://fakestoreapi.com/products/3")).thenReturn(jsonResponse);

        Optional<Product> product = productFinderService.findProductDetails(3);

        assertEquals(3, product.get().getId());
        assertEquals("Mens Cotton Jacket", product.get().getTitle());
    }

    @Test
    public void testFindProductDetails_InvalidId() throws IOException {
        when(httpClient.doHttpGet("https://fakestoreapi.com/products/300")).thenReturn("");

        Optional<Product> product = productFinderService.findProductDetails(300);

        assertFalse(product.isPresent());
    }
}
