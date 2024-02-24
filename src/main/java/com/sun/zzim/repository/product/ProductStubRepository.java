package com.sun.zzim.repository.product;

import com.sun.zzim.repository.product.datamodel.ProductDataModel;

import java.util.List;

public class ProductStubRepository implements IProductRepository {
    @Override
    public List<ProductDataModel> getProducts(int pageNumber, int size) {
        return null;
    }

    @Override
    public List<ProductDataModel> getProducts(List<Long> ids) {
        return null;
    }

    @Override
    public ProductDataModel getProduct(long productId) {
        if(productId == 0) {
            return null;
        }
        return new ProductDataModel();
    }
}
