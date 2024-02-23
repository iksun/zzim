package com.sun.zzim.service.auth;

import com.sun.zzim.repository.UserRepository;
import com.sun.zzim.repository.datamodel.UserDataModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserLoginExecutor implements IUserLoginExecutor {
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public UserLoginExecutor(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(UserLoginParam userLoginParam) {
        UserDataModel userDataModel = userRepository.findByLoginId(userLoginParam.getLoginId());
        if(userDataModel == null) {
            return null;
        }

        if(!Objects.equals(userDataModel.getLoginPw(), userLoginParam.getPassword())) {
            return null;
        }
        return jwtTokenProvider.generateToken(new UserDetail(userDataModel));
    }

}
