package com.sun.zzim.repository.zzim;

import java.util.List;

public interface IZzimRepository {
    ZzimDataModel save(ZzimDataModel zzimDataModel);
    boolean exist(long productId, long userId);

    ZzimDataModel findById(Long zzimId);

    void delete(ZzimDataModel zzimDataModel);

    List<ZzimDataModel> findAllByUserIdAndZzimBoxId(Long userId, Long zzimBoxId, int pageNumber, int size);
}
