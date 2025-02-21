package tqs;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class ProductFinderService {
    private static final String API_PRODUCTS = "https://fakestoreapi.com/products/";
    private final ISimpleHttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductFinderService(ISimpleHttpClient simpleHttpClient) {
        this.httpClient = simpleHttpClient;
    }

    public Optional<Product> findProductDetails(int id) {
        try {
            String response = httpClient.doHttpGet(API_PRODUCTS + id);
            if (response.isEmpty()) {
                return Optional.empty();
            }
            Product product = objectMapper.readValue(response, Product.class);
            return Optional.of(product);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
