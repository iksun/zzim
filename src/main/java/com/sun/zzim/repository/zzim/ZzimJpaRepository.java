package com.sun.zzim.repository.zzim;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZzimJpaRepository extends JpaRepository<ZzimDataModel, Long> {
    boolean existsByUserIdAndProductId(long userId, long productId);
    List<ZzimDataModel> findAllByUserIdAndZzimBoxId(Long userId, Long id, Pageable pageable);
    void deleteAllByZzimBoxId(long zzimBoxId);
}
