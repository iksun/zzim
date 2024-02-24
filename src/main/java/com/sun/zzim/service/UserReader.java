package com.sun.zzim.service;

import com.sun.zzim.repository.UserRepository;
import com.sun.zzim.repository.datamodel.UserDataModel;
import org.springframework.stereotype.Service;

@Service
public class UserReader implements IUserReader {
    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(long userId) {
        UserDataModel userDataModel = userRepository.findByUserId(userId);
        if(userDataModel == null) {
            return null;
        }

        return new User(
                userDataModel.getId(),
                userDataModel.getLoginId(),
                userDataModel.getLoginPw(),
                userDataModel.getCreateTime()
        );
    }
}
