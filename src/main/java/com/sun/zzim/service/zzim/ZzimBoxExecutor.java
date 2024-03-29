package com.sun.zzim.service.zzim;

import com.sun.zzim.controller.zzim.ZzimBoxDeleteParam;
import com.sun.zzim.repository.zzim.IZzimBoxRepository;
import com.sun.zzim.repository.zzim.IZzimRepository;
import com.sun.zzim.repository.zzim.ZzimBoxDataModel;
import com.sun.zzim.service.ZzimBoxCreateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZzimBoxExecutor implements IZzimBoxExecutor {
    private final IZzimBoxRepository repository;
    private final IZzimRepository zzimRepository;

    public ZzimBoxExecutor(IZzimBoxRepository repository, IZzimRepository zzimRepository) {
        this.repository = repository;
        this.zzimRepository = zzimRepository;
    }

    @Override
    public ZzimBox createBox(ZzimBoxCreateParam param) {
        if(repository.existSameNameBoxes(param.getUserId(), param.getName())) {
            return null;
        }
        ZzimBoxDataModel zzimBoxDataModel = repository.save(param.getName(), param.getUserId());
        if(zzimBoxDataModel == null) {
            return null;
        }
        return new ZzimBox(zzimBoxDataModel.getId(), zzimBoxDataModel.getName(), zzimBoxDataModel.getUserId());
    }

    @Transactional
    @Override
    public void deleteBox(ZzimBoxDeleteParam deleteParam) {
        ZzimBoxDataModel zzimBox = repository.findById(deleteParam.getBoxId());
        if(zzimBox == null) {
            return ;
        }
        zzimRepository.deleteByZzimBoxId(zzimBox.getId());
        repository.delete(zzimBox);
    }
}
