package com.sun.zzim.controller.zzim;

import com.sun.zzim.service.ZzimBoxCreateParam;
import com.sun.zzim.service.user.auth.UserDetail;
import com.sun.zzim.service.zzim.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ZzimBoxController {
    private final IZzimBoxReader zzimBoxReader;
    private final IZzimBoxExecutor zzimBoxExecutor;
    private final IZzimExecutor zzimExecutor;
    public ZzimBoxController(IZzimBoxReader zzimBoxReader, IZzimBoxExecutor zzimBoxExecutor, IZzimExecutor zzimExecutor) {
        this.zzimBoxReader = zzimBoxReader;
        this.zzimBoxExecutor = zzimBoxExecutor;
        this.zzimExecutor = zzimExecutor;
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
        if(zzimBox == null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(new ZzimboxResponse(zzimBox.getId(), zzimBox.getName(), zzimBox.getUserId()));
    }

    @DeleteMapping("/zzim-boxes/{boxId}")
    public ResponseEntity<Boolean> deleteBox(@AuthenticationPrincipal UserDetail userDetail,
                                             @PathVariable long boxId) {
        zzimBoxExecutor.deleteBox(new ZzimBoxDeleteParam(userDetail.getUserId(), boxId));
        return ResponseEntity.ok(true);
    }

    @PostMapping("/zzim-boxes/{boxId}/zzim")
    public ResponseEntity<Long> zzim(@AuthenticationPrincipal UserDetail userDetail,
                                        @PathVariable long boxId,
                                        @RequestBody ZzimCreateRequest zzimCreateRequest) {
        Zzim zzim = zzimExecutor.zzim(new ZzimParam(userDetail.getUserId(), boxId, zzimCreateRequest.getProductId()));
        if(zzim == null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(zzim.getId());
    }
}
