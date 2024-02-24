package com.sun.zzim.service.zzim;

public interface IZzimExecutor {
    public Zzim zzim(ZzimParam zzimParam);

    boolean delete(ZzimDeleteParam zzimDeleteParam);
}
