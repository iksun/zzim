package com.sun.zzim.repository.zzim;

import java.util.List;


public interface IZzimBoxRepository {
    List<ZzimBoxDataModel> findMyZzimBoxes(long userId, int pageNumber, int size);
    boolean existSameNameBoxes(long userId, String name);
    void save(String name, long userId);
}
