package com.sun.zzim.service.zzim;

import com.sun.zzim.repository.zzim.ZzimBoxStubRepository;
import com.sun.zzim.service.ZzimBoxCreateParam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZzimBoxExecutorTest {

    @Test
    void sut_zzim_box_create_success() {
        var sut = new ZzimBoxExecutor(new ZzimBoxStubRepository());
        ZzimBox box = sut.createBox(new ZzimBoxCreateParam(1L, "box"));
        assertNotNull(box);
        assertEquals(box.getUserId(), 1L);
    }

    @Test
    void sut_zzim_box_create_failed_duplicate_name() {
        var sut = new ZzimBoxExecutor(new ZzimBoxStubRepository());
        ZzimBox box1 = sut.createBox(new ZzimBoxCreateParam(1L, "box"));
        ZzimBox box2 = sut.createBox(new ZzimBoxCreateParam(1L, "box"));
        assertNotNull(box1);
        assertNull(box2);
    }
}