package com.sun.zzim.service.zzim;

import com.sun.zzim.repository.product.IProductRepository;
import com.sun.zzim.repository.zzim.IZzimRepository;
import com.sun.zzim.repository.zzim.ZzimDataModel;
import org.springframework.stereotype.Service;

@Service
public class ZzimExecutor implements IZzimExecutor {
    private final IZzimRepository zzimRepository;
    private final IProductRepository productRepository;
    public ZzimExecutor(IZzimRepository zzimRepository, IProductRepository productRepository) {
        this.zzimRepository = zzimRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Zzim zzim(ZzimParam zzimParam) {

        if(zzimParam.getZzimBoxId() == null) {
            return null;
        }

        if(productRepository.getProduct(zzimParam.getProductId()) == null) {
             return null;
        }

        if(zzimRepository.exist(zzimParam.getProductId(), zzimParam.getUserId())) {
            return null;
        }

        ZzimDataModel zzimDataModel = zzimRepository.save(
                new ZzimDataModel(
                        zzimParam.getUserId(),
                        zzimParam.getZzimBoxId(),
                        zzimParam.getProductId())
        );

        return new Zzim(
                zzimDataModel.getId(),
                zzimDataModel.getUserId(),
                zzimDataModel.getZzimBoxId(),
                zzimDataModel.getProductId()
        );
    }
}