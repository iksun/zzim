package com.sun.zzim.repository.user;

import com.sun.zzim.repository.user.datamodel.UserDataModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserStubRepository implements UserRepository {
    Map<String, UserDataModel> userMap = new HashMap<>();

    public UserStubRepository() {
    }

    public UserStubRepository(Map<String, UserDataModel> userMap) {
        this.userMap = userMap;
    }

    @Override
    public UserDataModel save(UserDataModel userDataModel) {
        if(userMap.keySet().stream().anyMatch(it -> Objects.equals(userDataModel.getLoginId(), it))) {
            return null;
        }

        userMap.put(userDataModel.getLoginId(), userDataModel);
        return userDataModel;
    }

    @Override
    public UserDataModel findByLoginId(String loginId) {
        return userMap.get(loginId);
    }

    @Override
    public UserDataModel findByUserId(long userId) {
        return null;
    }
}
