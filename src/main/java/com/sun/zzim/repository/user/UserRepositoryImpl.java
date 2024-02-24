package com.sun.zzim.repository.user;

import com.sun.zzim.repository.user.datamodel.UserDataModel;
import com.sun.zzim.repository.user.jpa.UserJpaRepository;
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

    @Override
    public UserDataModel findByUserId(long userId) {
        return userJpaRepository.findById(userId).orElse(null);
    }
}
