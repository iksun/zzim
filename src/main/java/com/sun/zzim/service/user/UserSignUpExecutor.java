package com.sun.zzim.service.user;

import com.sun.zzim.repository.user.UserRepository;
import com.sun.zzim.repository.user.datamodel.UserDataModel;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpExecutor implements IUserSignUpExecutor {
    private final UserRepository userRepository;

    public UserSignUpExecutor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signup(UserSignUpParam userSignUpParam) {
        try {
            UserDataModel saveModel = userRepository.save(
                    new UserDataModel(
                            userSignUpParam.getLoginId(),
                            userSignUpParam.getLoginPw())
            );

            if(saveModel == null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
