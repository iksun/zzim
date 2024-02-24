package com.sun.zzim.service;

import com.sun.zzim.repository.UserStubRepository;
import com.sun.zzim.repository.datamodel.UserDataModel;
import com.sun.zzim.service.auth.JwtTokenProviderFactory;
import com.sun.zzim.service.auth.UserLoginExecutor;
import com.sun.zzim.service.auth.UserLoginParam;
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
        String token = sut.login(new UserLoginParam(EXIST_LOGIN_ID, "test"));
        assertNotNull(token);
    }

    @Test
    void sut_login_fail_id_not_exist() {
        Map<String, UserDataModel> testData = makeTestData();
        var sut = new UserLoginExecutor(new UserStubRepository(testData), new JwtTokenProviderFactory(new UserStubRepository(), TEST_KEY));
        String token = sut.login(new UserLoginParam("test", "test"));
        assertNull(token);
    }

    @Test
    void sut_login_fail_password_not_matched() {
        Map<String, UserDataModel> testData = makeTestData();
        var sut = new UserLoginExecutor(new UserStubRepository(testData), new JwtTokenProviderFactory(new UserStubRepository(), TEST_KEY));
        String token = sut.login(new UserLoginParam(EXIST_LOGIN_ID, "test2"));
        assertNull(token);
    }
    private Map<String, UserDataModel> makeTestData() {
        Map<String, UserDataModel> userMap = new HashMap<>();
        userMap.put("testId", new UserDataModel(EXIST_LOGIN_ID, "test"));
        return userMap;
    }

}