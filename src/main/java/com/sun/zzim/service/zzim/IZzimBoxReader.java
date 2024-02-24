package com.sun.zzim.service.zzim;

import java.util.List;

public interface IZzimBoxReader {
    List<ZzimBox> getMyZzimBox(long userId, int pageNumber, int size);
}
