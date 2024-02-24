package com.sun.zzim.repository.user;

import com.sun.zzim.repository.user.datamodel.UserDataModel;

public interface UserRepository {
    UserDataModel save(UserDataModel userDataModel);

    UserDataModel findByLoginId(String loginId);

    UserDataModel findByUserId(long userId);
}
