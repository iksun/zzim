package com.sun.zzim.service.zzim;

import com.sun.zzim.repository.zzim.IZzimBoxRepository;
import com.sun.zzim.service.ZzimBoxCreateParam;
import org.springframework.stereotype.Service;

@Service
public class ZzimBoxExecutor implements IZzimBoxExecutor {
    private final IZzimBoxRepository repository;

    public ZzimBoxExecutor(IZzimBoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createBox(ZzimBoxCreateParam param) {
        if(repository.existSameNameBoxes(param.getUserId(), param.getName())) {
            return ;
        }

        repository.save(param.getName(), param.getUserId());
    }
}
