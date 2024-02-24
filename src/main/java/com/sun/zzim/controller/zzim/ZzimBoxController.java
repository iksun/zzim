package com.sun.zzim.controller.zzim;

import com.sun.zzim.service.ZzimBoxCreateParam;
import com.sun.zzim.service.user.auth.UserDetail;
import com.sun.zzim.service.zzim.IZzimBoxExecutor;
import com.sun.zzim.service.zzim.IZzimBoxReader;
import com.sun.zzim.service.zzim.ZzimBox;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ZzimBoxController {
    private final IZzimBoxReader zzimBoxReader;
    private final IZzimBoxExecutor zzimBoxExecutor;

    public ZzimBoxController(IZzimBoxReader zzimBoxReader, IZzimBoxExecutor zzimBoxExecutor) {
        this.zzimBoxReader = zzimBoxReader;
        this.zzimBoxExecutor = zzimBoxExecutor;
    }

    @GetMapping("/zzim-boxes")
    public ResponseEntity<List<ZzimboxResponse>> getMyBoxes(@AuthenticationPrincipal UserDetail userDetail,
                                                            @RequestParam int pageNumber ,
                                                            @RequestParam int size) {
        return ResponseEntity.ok(
                zzimBoxReader.getMyZzimBox(userDetail.getUserId(), pageNumber, size)
                        .stream()
                        .map(it-> new ZzimboxResponse(it.getId(), it.getName(), it.getUserId()))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/zzim-boxes")
    public ResponseEntity<ZzimboxResponse> createBox(@AuthenticationPrincipal UserDetail userDetail,
                                             @RequestBody ZzimBoxCreateRequest zzimBoxCreateRequest) {
        ZzimBox zzimBox = zzimBoxExecutor.createBox(new ZzimBoxCreateParam(userDetail.getUserId(), zzimBoxCreateRequest.getName()));
        return ResponseEntity.ok(new ZzimboxResponse(zzimBox.getId(), zzimBox.getName(), zzimBox.getUserId()));
    }

}
