package com.sun.zzim.controller.zzim;

import com.sun.zzim.service.user.auth.UserDetail;
import com.sun.zzim.service.zzim.IZzimBoxReader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ZzimBoxController {
    private final IZzimBoxReader zzimBoxReader;

    public ZzimBoxController(IZzimBoxReader zzimBoxReader) {
        this.zzimBoxReader = zzimBoxReader;
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
}
