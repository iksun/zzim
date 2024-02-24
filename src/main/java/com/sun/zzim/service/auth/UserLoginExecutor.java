package com.sun.zzim.service.auth;

import com.sun.zzim.repository.UserRepository;
import com.sun.zzim.repository.datamodel.UserDataModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserLoginExecutor implements IUserLoginExecutor {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserLoginExecutor(UserRepository userRepository, JwtTokenProviderFactory jwtTokenProviderFactory) {
        this.userRepository = userRepository;
        jwtTokenProvider = jwtTokenProviderFactory.getJwtTokenProvider();
    }

    @Override
    public UserLoginResult login(UserLoginParam userLoginParam) {
        UserDataModel userDataModel = userRepository.findByLoginId(userLoginParam.getLoginId());
        if(userDataModel == null) {
            return new UserLoginResult(false, 0l, null);
        }

        if(!Objects.equals(userDataModel.getLoginPw(), userLoginParam.getPassword())) {
            return new UserLoginResult(false, 0l, null);
        }
        return new UserLoginResult(
                true,
                userDataModel.getId(),
                jwtTokenProvider.generateToken(new UserDetail(userDataModel))
        );
    }

}
