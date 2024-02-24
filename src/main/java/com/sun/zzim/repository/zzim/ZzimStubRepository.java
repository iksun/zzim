package com.sun.zzim.repository.zzim;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ZzimStubRepository implements IZzimRepository {
    List<ZzimDataModel> zzims = new ArrayList<>();
    @Override
    public ZzimDataModel save(ZzimDataModel zzimDataModel) {
        zzims.add(zzimDataModel);
        return zzimDataModel;
    }

    @Override
    public boolean exist(long productId, long userId) {
        return zzims
                .stream()
                .anyMatch(it -> Objects.equals(it.getProductId(), productId)
                        && Objects.equals(it.getUserId(), userId)
                );
    }

    @Override
    public ZzimDataModel findById(Long zzimId) {
        return zzims.get(0);
    }

    @Override
    public void delete(ZzimDataModel zzimDataModel) {

    }

    @Override
    public List<ZzimDataModel> findAllByUserIdAndZzimBoxId(Long userId, Long zzimBoxId, int pageNumber, int size) {
        return null;
    }

    @Override
    public void deleteByZzimBoxId(Long zzimBoxId) {

    }
}
