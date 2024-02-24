package com.sun.zzim.controller.product;

import com.sun.zzim.service.product.ProductReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductReader productReader;

    public ProductController(ProductReader productReader) {
        this.productReader = productReader;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam int pageNumber , @RequestParam int size) {
        return ResponseEntity.ok(
                productReader.getAllProducts(pageNumber, size)
                        .stream()
                        .map(it -> new ProductResponse(it.getId(), it.getName(), it.getThumbnail(), it.getPrice()))
                        .collect(Collectors.toList())
        );
    }
}
