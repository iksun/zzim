package com.sun.zzim.repository.product;

import com.sun.zzim.repository.product.datamodel.ProductDataModel;
import com.sun.zzim.repository.product.jpa.ProductJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepository implements IProductRepository {
    private final ProductJpaRepository productJpaRepository;

    public ProductRepository(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<ProductDataModel> getProducts(int pageNumber, int size) {
        Page<ProductDataModel> result = productJpaRepository.findAll(PageRequest.of(pageNumber, size));
        return result.getContent();
    }

    @Override
    public List<ProductDataModel> getProducts(List<Long> ids) {
        return productJpaRepository.findAllById(ids);
    }

    @Override
    public ProductDataModel getProduct(long productId) {
        return productJpaRepository.findById(productId).orElse(null);
    }
}
