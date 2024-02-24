package com.sun.zzim.controller.product;

import com.sun.zzim.service.product.ProductReader;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "상품 목록 조회", description = "등록된 상품을 조회합니다.")
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                productReader.getAllProducts(pageNumber, size)
                        .stream()
                        .map(it -> new ProductResponse(it.getId(), it.getName(), it.getThumbnail(), it.getPrice()))
                        .collect(Collectors.toList())
        );
    }
}
