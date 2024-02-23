package com.sun.zzim.repository;

import com.sun.zzim.repository.datamodel.UserDataModel;

import java.util.HashMap;
import java.util.Map;

public class UserStubRepository implements UserRepository {
    Map<String, UserDataModel> userMap = new HashMap<>();
    @Override
    public UserDataModel save(UserDataModel userDataModel) {
        userMap.put(userDataModel.getLoginId(), userDataModel);
        return userDataModel;
    }
}
