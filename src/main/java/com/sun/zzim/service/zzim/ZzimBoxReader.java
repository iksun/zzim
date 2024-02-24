package com.sun.zzim.service.zzim;

import com.sun.zzim.repository.zzim.IZzimBoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZzimBoxReader implements IZzimBoxReader {
    private final IZzimBoxRepository repository;

    public ZzimBoxReader(IZzimBoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ZzimBox> getMyZzimBox(long userId, int pageNumber, int size) {
        return repository.findMyZzimBoxes(userId, pageNumber, size)
                .stream()
                .map(it -> new ZzimBox(it.getId(), it.getName(), it.getUserId()))
                .collect(Collectors.toList());
    }
}
