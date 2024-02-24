package com.sun.zzim.controller.zzim;

import com.sun.zzim.controller.product.ProductResponse;
import com.sun.zzim.service.ZzimBoxCreateParam;
import com.sun.zzim.service.user.auth.UserDetail;
import com.sun.zzim.service.zzim.*;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "찜박스 목록", description = "나의 찜박스 목록을 출력합니다.")
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
    @Operation(summary = "찜박스 생성", description = "찜박스를 생성합니다.")
    @PostMapping("/zzim-boxes")
    public ResponseEntity<ZzimboxResponse> createBox(@AuthenticationPrincipal UserDetail userDetail,
                                             @RequestBody ZzimBoxCreateRequest zzimBoxCreateRequest) {
        ZzimBox zzimBox = zzimBoxExecutor.createBox(new ZzimBoxCreateParam(userDetail.getUserId(), zzimBoxCreateRequest.getName()));
        if(zzimBox == null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(new ZzimboxResponse(zzimBox.getId(), zzimBox.getName(), zzimBox.getUserId()));
    }

    @Operation(summary = "찜박스 삭제", description = "찜박스를 삭제합니다.")
    @DeleteMapping("/zzim-boxes/{boxId}")
    public ResponseEntity<Boolean> deleteBox(@AuthenticationPrincipal UserDetail userDetail,
                                             @PathVariable long boxId) {
        zzimBoxExecutor.deleteBox(new ZzimBoxDeleteParam(userDetail.getUserId(), boxId));
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "찜 삭제", description = "찜을 삭제합니다.")
    @DeleteMapping("/zzim-boxes/{boxId}/zzim/{zzimId}")
    public ResponseEntity<Boolean> delete(@AuthenticationPrincipal UserDetail userDetail,
                                          @PathVariable long boxId,
                                          @PathVariable long zzimId) {
        zzimExecutor.delete(new ZzimDeleteParam(boxId, zzimId, userDetail.getUserId()));
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "찜하기", description = "해당 찜박스에 상품을 찜합니다.")
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
    @Operation(summary = "찜박스 내 찜 목록 가져오기", description = "해당 찜박스내의 찜 목록을 가져옵니다.")
    @GetMapping("/zzim-boxes/{boxId}")
    public ResponseEntity<List<ZzimResponse>> getZzims(@AuthenticationPrincipal UserDetail userDetail,
                                                      @PathVariable long boxId,
                                                      @RequestParam int pageNumber ,
                                                      @RequestParam int size) {
        List<Zzim> zzimsInBox = zzimBoxReader.getZzimInBox(userDetail.getUserId(), boxId, pageNumber, size);
        return ResponseEntity.ok(
                zzimsInBox
                        .stream()
                        .map(it-> new ZzimResponse(
                                it.getId(),
                                it.getUserId(),
                                it.getZzimBoxId(),
                                new ProductResponse(
                                        it.getProduct().getId(),
                                        it.getProduct().getName(),
                                        it.getProduct().getThumbnail(),
                                        it.getProduct().getPrice())))
                        .collect(Collectors.toList()));
    }


}
