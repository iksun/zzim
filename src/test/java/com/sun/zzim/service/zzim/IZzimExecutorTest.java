package com.sun.zzim.service.zzim;

import com.sun.zzim.repository.product.ProductStubRepository;
import com.sun.zzim.repository.zzim.ZzimStubRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IZzimExecutorTest {
    @Test
    void sut_zzim_success() {
        var sut = new ZzimExecutor(new ZzimStubRepository(), new ProductStubRepository());
        Zzim zzim = sut.zzim(new ZzimParam(1L, 1L, 1l));
        assertNotNull(zzim);
    }

    @Test
    void sut_zzim_failed_box_id_null () {
        var sut = new ZzimExecutor(new ZzimStubRepository(), new ProductStubRepository());
        Zzim zzim = sut.zzim(new ZzimParam(1L, null, 1l));
        assertNull(zzim);
    }
    @Test
    void sut_zzim_failed_product_not_exist () {
        var sut = new ZzimExecutor(new ZzimStubRepository(), new ProductStubRepository());
        Zzim zzim = sut.zzim(new ZzimParam(1L, 1L, 0l));
        assertNull(zzim);
    }

    @Test
    void sut_zzim_failed_same_product_already_zzim () {
        var sut = new ZzimExecutor(new ZzimStubRepository(), new ProductStubRepository());
        Zzim zzim = sut.zzim(new ZzimParam(1L, 1L, 1l));
        Zzim zzim2 = sut.zzim(new ZzimParam(1L, 1L, 1l));
        assertNotNull(zzim);
        assertNull(zzim2);
    }
}