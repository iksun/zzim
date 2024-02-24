package com.sun.zzim.repository.zzim;

import java.util.*;

public class ZzimBoxStubRepository implements IZzimBoxRepository {
    List<ZzimBoxDataModel> list = new ArrayList<>();
    @Override
    public List<ZzimBoxDataModel> findMyZzimBoxes(long userId, int pageNumber, int size) {
        return null;
    }

    @Override
    public boolean existSameNameBoxes(long userId, String name) {
        return list.stream().filter(it -> Objects.equals(it.getName(), name) && Objects.equals(it.getUserId(), userId)).findAny().isPresent();
    }

    @Override
    public ZzimBoxDataModel save(String name, long userId) {
        ZzimBoxDataModel zzimBoxDataModel = new ZzimBoxDataModel(name, userId);
        list.add(zzimBoxDataModel);
        return zzimBoxDataModel;
    }

    @Override
    public void delete(ZzimBoxDataModel zzimBoxDataModel) {

    }

    @Override
    public ZzimBoxDataModel findById(long zzimBoxId) {
        return null;
    }
}
