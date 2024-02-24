package com.sun.zzim.repository.zzim;

import java.util.List;

public interface IZzimRepository {
    ZzimDataModel save(ZzimDataModel zzimDataModel);
    boolean exist(long productId, long userId);
}
