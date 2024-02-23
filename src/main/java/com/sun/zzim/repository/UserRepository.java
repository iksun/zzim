package com.sun.zzim.repository;

import com.sun.zzim.repository.datamodel.UserDataModel;

public interface UserRepository {
    UserDataModel save(UserDataModel userDataModel);
}
