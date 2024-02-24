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
}