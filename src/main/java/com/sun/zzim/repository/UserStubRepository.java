package com.sun.zzim.repository;

import com.sun.zzim.repository.datamodel.UserDataModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserStubRepository implements UserRepository {
    Map<String, UserDataModel> userMap = new HashMap<>();
    @Override
    public UserDataModel save(UserDataModel userDataModel) {
        if(userMap.keySet().stream().anyMatch(it -> Objects.equals(userDataModel.getLoginId(), it))) {
            return null;
        }

        userMap.put(userDataModel.getLoginId(), userDataModel);
        return userDataModel;
    }
}
