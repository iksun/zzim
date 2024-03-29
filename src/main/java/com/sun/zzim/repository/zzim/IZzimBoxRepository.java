package com.sun.zzim.repository.zzim;

import java.util.List;


public interface IZzimBoxRepository {
    List<ZzimBoxDataModel> findMyZzimBoxes(long userId, int pageNumber, int size);
    boolean existSameNameBoxes(long userId, String name);
    ZzimBoxDataModel save(String name, long userId);
    void delete(ZzimBoxDataModel zzimBoxDataModel);

    ZzimBoxDataModel findById(long zzimBoxId);

}
