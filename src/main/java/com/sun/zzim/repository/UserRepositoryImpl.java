package com.sun.zzim.repository;

import com.sun.zzim.repository.datamodel.UserDataModel;
import com.sun.zzim.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDataModel save(UserDataModel userDataModel) {
        UserDataModel saveModel = userJpaRepository.save(userDataModel);
        return saveModel;
    }

    @Override
    public UserDataModel findByLoginId(String loginId) {
        return userJpaRepository.findByLoginId(loginId);
    }
}
