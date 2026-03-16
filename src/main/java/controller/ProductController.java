package controller;

import model.Product;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public void index(@RequestBody Product product) throws Exception {
        service.indexProduct(product);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String text) throws Exception {
        return service.search(text);
    }
}
