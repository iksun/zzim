package com.sun.zzim.repository.zzim;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ZzimBoxRepository implements IZzimBoxRepository {
    private final ZzimBoxJpaRepository repository;

    public ZzimBoxRepository(ZzimBoxJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ZzimBoxDataModel> getMyZzimBoxes(long userId, int pageNumber, int size) {
        return repository.findAllByUserId(userId, PageRequest.of(pageNumber, size));
    }
}
