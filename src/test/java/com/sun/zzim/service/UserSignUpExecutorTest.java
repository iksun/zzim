package com.sun.zzim.service;

import com.sun.zzim.repository.UserStubRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSignUpExecutorTest {
    @Test
    public void sut_signup_success() {
        var sut = new UserSignUpExecutor(new UserStubRepository());
        assertTrue(sut.signup(new UserSignUpParam("test", "test")));
    }

    @Test
    public void sut_signup_fail_login_id_dup() {
        var sut = new UserSignUpExecutor(new UserStubRepository());
        sut.signup(new UserSignUpParam("test", "test1"));

        assertFalse(sut.signup(new UserSignUpParam("test", "test1")));
    }
}