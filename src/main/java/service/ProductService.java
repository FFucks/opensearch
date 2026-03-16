package service;

import model.Product;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final OpenSearchClient client;

    public ProductService(OpenSearchClient client) {
        this.client = client;
    }

    public void indexProduct(Product product) throws IOException {

        client.index(i -> i
                .index("products")
                .id(product.getId())
                .document(product)
        );
    }

    public List<Product> search(String text) throws IOException {

        var response = client.search(s -> s
                        .index("products")
                        .query(q -> q
                                .match(m -> m
                                        .field("name")
                                        .query(FieldValue.of(text)))),
                Product.class
        );

        return response.hits()
                        .hits()
                        .stream()
                        .map(hit -> hit.source())
                        .toList();
    }
}
