package com.sun.zzim.service.auth;

import com.sun.zzim.repository.user.UserStubRepository;
import com.sun.zzim.repository.user.datamodel.UserDataModel;
import com.sun.zzim.service.user.auth.JwtTokenProvider;
import com.sun.zzim.service.user.auth.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {
    private static final String TEST_KEY = "jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313";

    @Test
    void sut_validate_success() {
        var sut = new JwtTokenProvider(new UserStubRepository(new HashMap<>()), TEST_KEY);
        String token = sut.generateToken(new UserDetail(new UserDataModel("test", "test")));
        assertTrue(sut.validate(token));
    }

    @Test
    void sut_validate_failed_token_expired() {
        var sut = new JwtTokenProvider(new UserStubRepository(new HashMap<>()), TEST_KEY);
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aCI6IlVTRVIiLCJleHAiOjE3MDg3MzIzODV9.QY4L7shAxfmOEObEQcbx7FxXb9FgWQN3otF3TgJkyrc";
        assertFalse(sut.validate(token));
    }

    @Test
    void sut_getAuthentication_success() {

        HashMap<String, UserDataModel> testUserMap = new HashMap<>();
        String username = "test";
        UserDataModel userDataModel = new UserDataModel(username, "test");
        testUserMap.put(username, new UserDataModel(username, "test"));
        //-- set data

        var sut = new JwtTokenProvider(new UserStubRepository(testUserMap), TEST_KEY);
        String token = sut.generateToken(new UserDetail(userDataModel));
        Authentication authentication = sut.getAuthentication(token);

        assertNotNull(authentication);
        assertEquals(username, authentication.getName());
    }
    @Test
    void sut_sut_getAuthentication_failed_user_not_exist() {
        String username = "test";
        UserDataModel userDataModel = new UserDataModel(username, "test");

        var sut = new JwtTokenProvider(new UserStubRepository(), TEST_KEY);
        String token = sut.generateToken(new UserDetail(userDataModel));

        Authentication authentication = sut.getAuthentication(token);

        assertNull(authentication);
    }
}