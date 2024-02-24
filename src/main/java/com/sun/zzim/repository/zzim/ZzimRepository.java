package com.sun.zzim.repository.zzim;

import org.springframework.stereotype.Repository;

@Repository
public class ZzimRepository implements IZzimRepository {
    private final ZzimJpaRepository zzimJpaRepository;

    public ZzimRepository(ZzimJpaRepository zzimJpaRepository) {
        this.zzimJpaRepository = zzimJpaRepository;
    }

    @Override
    public ZzimDataModel save(ZzimDataModel zzimDataModel) {
        return zzimJpaRepository.save(zzimDataModel);
    }

    @Override
    public boolean exist(long productId, long userId) {
        return zzimJpaRepository.existsByUserIdAndProductId(userId, productId);
    }

    @Override
    public ZzimDataModel findById(Long zzimId) {
        return zzimJpaRepository.findById(zzimId).orElse(null);
    }

    @Override
    public void delete(ZzimDataModel zzimDataModel) {
        zzimJpaRepository.delete(zzimDataModel);
    }
}
