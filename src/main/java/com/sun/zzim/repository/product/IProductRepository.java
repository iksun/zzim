package com.sun.zzim.repository.product;

import com.sun.zzim.repository.product.datamodel.ProductDataModel;

import java.util.List;

public interface IProductRepository {
    public List<ProductDataModel> getProducts(int pageNumber , int size);
    public List<ProductDataModel> getProducts(List<Long> ids);
    public ProductDataModel getProduct(long productId);

}
