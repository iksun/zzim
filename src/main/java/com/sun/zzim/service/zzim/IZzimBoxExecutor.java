package com.sun.zzim.service.zzim;

import com.sun.zzim.controller.zzim.ZzimBoxDeleteParam;
import com.sun.zzim.service.ZzimBoxCreateParam;

public interface IZzimBoxExecutor {
        public ZzimBox createBox(ZzimBoxCreateParam param);
        public void deleteBox(ZzimBoxDeleteParam deleteParam);

}
