package com.sun.zzim.repository.zzim;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZzimBoxJpaRepository extends JpaRepository<ZzimBoxDataModel, Long> {
    List<ZzimBoxDataModel> findAllByUserId(long userId, Pageable pageable);
    boolean existsByUserIdAndName(long userId, String name);
}
