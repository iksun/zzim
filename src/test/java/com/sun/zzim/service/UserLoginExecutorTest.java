package com.sun.zzim.service;

import com.sun.zzim.repository.user.UserStubRepository;
import com.sun.zzim.repository.user.datamodel.UserDataModel;
import com.sun.zzim.service.user.auth.JwtTokenProviderFactory;
import com.sun.zzim.service.user.auth.UserLoginExecutor;
import com.sun.zzim.service.user.auth.UserLoginParam;
import com.sun.zzim.service.user.auth.UserLoginResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginExecutorTest {
    private static final String EXIST_LOGIN_ID = "testId";
    private static final String TEST_KEY = "jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313";
    @Test
    void sut_login_success() {
        UserLoginExecutor sut = new UserLoginExecutor(new UserStubRepository(makeTestData()), new JwtTokenProviderFactory(new UserStubRepository(), TEST_KEY));
        UserLoginResult result = sut.login(new UserLoginParam(EXIST_LOGIN_ID, "test"));
        assertNotNull(result.getJwtToken());
    }

    @Test
    void sut_login_fail_id_not_exist() {
        Map<String, UserDataModel> testData = makeTestData();
        var sut = new UserLoginExecutor(new UserStubRepository(testData), new JwtTokenProviderFactory(new UserStubRepository(), TEST_KEY));
        UserLoginResult result = sut.login(new UserLoginParam("test", "test"));
        assertNull(result.getJwtToken());
    }

    @Test
    void sut_login_fail_password_not_matched() {
        Map<String, UserDataModel> testData = makeTestData();
        var sut = new UserLoginExecutor(new UserStubRepository(testData), new JwtTokenProviderFactory(new UserStubRepository(), TEST_KEY));
        UserLoginResult result = sut.login(new UserLoginParam(EXIST_LOGIN_ID, "test2"));
        assertNull(result.getJwtToken());
    }
    private Map<String, UserDataModel> makeTestData() {
        Map<String, UserDataModel> userMap = new HashMap<>();
        userMap.put("testId", new UserDataModel(EXIST_LOGIN_ID, "test"));
        return userMap;
    }

}