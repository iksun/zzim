package com.sun.zzim.repository.zzim;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ZzimBoxRepository implements IZzimBoxRepository {
    private final ZzimBoxJpaRepository repository;

    public ZzimBoxRepository(ZzimBoxJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ZzimBoxDataModel> findMyZzimBoxes(long userId, int pageNumber, int size) {
        return repository.findAllByUserId(userId, PageRequest.of(pageNumber, size));
    }

    @Override
    public ZzimBoxDataModel save(String name, long userId) {
        return repository.save(new ZzimBoxDataModel(name, userId));
    }

    @Override
    public boolean existSameNameBoxes(long userId, String name) {
        return repository.existsByUserIdAndName(userId, name);
    }
    @Override
    public void delete(ZzimBoxDataModel zzimBoxDataModel) {
        repository.delete(zzimBoxDataModel);
    }

    @Override
    public ZzimBoxDataModel findById(long zzimBoxId) {
        Optional<ZzimBoxDataModel> zzimBoxOptional = repository.findById(zzimBoxId);
        return zzimBoxOptional.orElse(null);
    }
}
