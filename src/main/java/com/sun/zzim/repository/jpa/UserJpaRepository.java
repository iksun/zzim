package com.sun.zzim.repository.jpa;

import com.sun.zzim.repository.datamodel.UserDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDataModel, Long> {
    public UserDataModel findByLoginId(String loginId);
}
