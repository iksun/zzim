package com.sun.zzim.service.zzim;

import java.util.List;

public interface IZzimBoxReader {
    List<ZzimBox> getMyZzimBox(long userId, int pageNumber, int size);

    List<Zzim> getZzimInBox(Long userId, Long zzimBoxId, int pageNumber, int size);
}
