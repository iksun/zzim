package com.sun.zzim.repository.zzim;

import java.util.List;


public interface IZzimBoxRepository {
    List<ZzimBoxDataModel> getMyZzimBoxes(long userId, int pageNumber, int size);
}
