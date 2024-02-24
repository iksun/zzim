package com.sun.zzim.service.zzim;

import com.sun.zzim.repository.product.IProductRepository;
import com.sun.zzim.repository.product.datamodel.ProductDataModel;
import com.sun.zzim.repository.zzim.IZzimBoxRepository;
import com.sun.zzim.repository.zzim.IZzimRepository;
import com.sun.zzim.repository.zzim.ZzimDataModel;
import com.sun.zzim.service.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ZzimBoxReader implements IZzimBoxReader {
    private final IZzimBoxRepository repository;
    private final IZzimRepository zzimRepository;
    private final IProductRepository productRepository;

    public ZzimBoxReader(IZzimBoxRepository repository, IZzimRepository zzimRepository, IProductRepository productRepository) {
        this.repository = repository;
        this.zzimRepository = zzimRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ZzimBox> getMyZzimBox(long userId, int pageNumber, int size) {
        return repository.findMyZzimBoxes(userId, pageNumber, size)
                .stream()
                .map(it -> new ZzimBox(it.getId(), it.getName(), it.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Zzim> getZzimInBox(Long userId, Long zzimBoxId, int pageNumber, int size) {
        List<ZzimDataModel> result = zzimRepository.findAllByUserIdAndZzimBoxId(userId, zzimBoxId, pageNumber, size);
        List<ProductDataModel> products = productRepository.getProducts(result.stream().map(ZzimDataModel::getProductId).collect(Collectors.toList()));
        return result
                .stream()
                .map(it -> makeZzim(
                        it,
                        Objects.requireNonNull(
                                products
                                        .stream()
                                        .filter(product ->
                                                Objects.equals(
                                                        it.getProductId(),
                                                        product.getId()))
                                        .findFirst()
                                        .orElse(null))))
                .collect(Collectors.toList());
    }
    private Zzim makeZzim(ZzimDataModel zzimDataModel, ProductDataModel productDataModel) {
        return new Zzim(
                zzimDataModel.getId(),
                zzimDataModel.getUserId(),
                zzimDataModel.getZzimBoxId(),
                new Product(
                        productDataModel.getId(),
                        productDataModel.getName(),
                        productDataModel.getThumbnail(),
                        productDataModel.getPrice()
                )
        );
    }
}
