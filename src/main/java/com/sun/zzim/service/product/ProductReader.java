package com.sun.zzim.service.product;

import com.sun.zzim.repository.product.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductReader {
    private final IProductRepository productRepository;

    public ProductReader(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(int pageNumber , int size) {
        return productRepository.getProducts(pageNumber, size)
                .stream()
                .map(it -> new Product(it.getId(), it.getName(), it.getThumbnail(), it.getPrice()))
                .collect(Collectors.toList());
    }
}
