package com.sun.zzim.repository.zzim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZzimJpaRepository extends JpaRepository<ZzimDataModel, Long> {
    boolean existsByUserIdAndProductId(long userId, long productId);
}
