package com.mycom.myapp.codes.controller;

import com.mycom.myapp.codes.dto.CodeDto;
import com.mycom.myapp.codes.dto.CodeResultDto;
import com.mycom.myapp.codes.entity.key.CodeKey;
import com.mycom.myapp.codes.service.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CodeController {

    private static final Map<String, HttpStatus> STATUS_MAP = Map.of(
            "success", HttpStatus.OK,
            "not found", HttpStatus.BAD_REQUEST
    );

    private final CodeService codeService;

    @GetMapping("/codes")
    @Operation(summary = "코드 리스트 조회", description = "groupCode 에 해당하는 코드 리스트를 페이지네이션하여 조회합니다.")
    public ResponseEntity<CodeResultDto> listCode(
            @RequestParam("groupCode") String groupCode,
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return createResponse(codeService.listCode(groupCode, pageNumber, pageSize));
    }

    @GetMapping("/codes/{groupCode}/{code}")
    @Operation(summary = "단일 코드 조회", description = "그룹코드와 코드에 해당하는 코드를 상세 조회합니다.")
    public ResponseEntity<CodeResultDto> detailCode(@PathVariable("groupCode") String groupCode,
                                                    @PathVariable("code") String code) {
        CodeKey codeKey = new CodeKey(groupCode, code);
        return createResponse(codeService.detailCode(codeKey));
    }

    @PostMapping("/codes")
    @Operation(summary = "코드 생성", description = "코드를 생성합니다.")
    public ResponseEntity<CodeResultDto> insertCode(CodeDto codeDto) {
        return createResponse(codeService.insertCode(codeDto));
    }

    @PutMapping("/codes")
    @Operation(summary = "코드 수정", description = "코드키에 해당하는 코드를 수정합니다.")
    public ResponseEntity<CodeResultDto> updateCode(CodeDto codeDto) {
        return createResponse(codeService.updateCode(codeDto));
    }

    @DeleteMapping("/codes/{groupCode}/{code}")
    @Operation(summary = "코드 삭제", description = "그룹코드와 코드에 해당하는 코드를 삭제합니다.")
    public ResponseEntity<CodeResultDto> deleteCode(@PathVariable("groupCode") String groupCode,
                                                    @PathVariable("code") String code) {
        CodeKey codeKey = new CodeKey(groupCode, code);
        return createResponse(codeService.deleteCode(codeKey));
    }

    private ResponseEntity<CodeResultDto> createResponse(CodeResultDto result) {
        String resultStatus = result.getResult();
        HttpStatus status = STATUS_MAP.getOrDefault(resultStatus, HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(status).body(result);
    }
}
