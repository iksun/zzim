package com.sun.zzim.repository.product.jpa;

import com.sun.zzim.repository.product.datamodel.ProductDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductDataModel, Long> {
    @Override
    Page<ProductDataModel> findAll(Pageable pageable);
}
