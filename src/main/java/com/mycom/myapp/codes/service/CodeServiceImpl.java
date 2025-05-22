package com.mycom.myapp.codes.service;

import com.mycom.myapp.codes.dto.CodeDto;
import com.mycom.myapp.codes.dto.CodeResultDto;
import com.mycom.myapp.codes.entity.Code;
import com.mycom.myapp.codes.entity.key.CodeKey;
import com.mycom.myapp.codes.repository.CodeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodeServiceImpl implements CodeService{

    private final CodeRepository codeRepository;

    @Override
    public CodeResultDto insertCode(CodeDto codeDto) {
        CodeResultDto result = new CodeResultDto();

        try {
            Code code = convertToEntity(codeDto);
            codeRepository.save(code);
            result.setResult("success");

        } catch (Exception e) {
            log.error("코드 생성 중 오류 발생", e);
            result.setResult("fail");
        }

        return result;
    }

    @Override
    public CodeResultDto updateCode(CodeDto codeDto) {
        CodeResultDto result = new CodeResultDto();

        try {
            CodeKey codeKey = new CodeKey(codeDto.getGroupCode(), codeDto.getCode());
            boolean exists = codeRepository.existsById(codeKey);

            if (!exists) {
                result.setResult("not found");
                return result;
            }

            Code code = convertToEntity(codeDto);
            codeRepository.save(code);
            result.setResult("success");

        } catch (Exception e) {
            log.error("코드 업데이트 중 오류 발생", e);
            result.setResult("fail");
        }

        return result;
    }

    @Override
    public CodeResultDto deleteCode(CodeKey codeKey) {
        CodeResultDto result = new CodeResultDto();

        try {
            codeRepository.deleteById(codeKey);
            result.setResult("success");

        } catch (Exception e) {
            log.error("코드 삭제 중 오류 발생", e);
            result.setResult("fail");
        }

        return result;
    }

    @Override
    public CodeResultDto listCode(String groupCode, int pageNumber, int pageSize) {
        CodeResultDto result = new CodeResultDto();

        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Code> page = codeRepository.findByGroupCode(groupCode, pageable);

            List<CodeDto> codeDtoList = new ArrayList<>();
            page.toList().forEach(code -> codeDtoList.add(CodeDto.fromCode(code)));

            Long count = codeRepository.count();

            result.setCodeDtoList(codeDtoList);
            result.setCount(count);

            result.setResult("success");

        } catch (Exception e) {
            log.error("코드 전체 조회 중 오류 발생", e);
            result.setResult("fail");
        }

        return result;
    }

    @Override
    public CodeResultDto detailCode(CodeKey codeKey) {
        CodeResultDto result = new CodeResultDto();

        try {
            Optional<Code> optionalCode = codeRepository.findById(codeKey);

            optionalCode.ifPresentOrElse(
                    detailCode -> {
                        result.setCodeDto(CodeDto.fromCode(detailCode));
                        result.setResult("success");
                    },
                    () -> result.setResult("fail")
            );

        } catch (Exception e) {
            log.error("코드 단일 조회 중 오류 발생", e);
            result.setResult("fail");
        }

        return result;
    }

    @Override
    public CodeResultDto countCode() {
        CodeResultDto result = new CodeResultDto();

        try {
            Long count = codeRepository.count();

            result.setCount(count);
            result.setResult("success");

        } catch (Exception e) {
            log.error("코드 개수 조회 중 오류 발생", e);
            result.setResult("fail");
        }

        return result;
    }

    private Code convertToEntity(CodeDto codeDto) {
        CodeKey codeKey = new CodeKey(codeDto.getGroupCode(), codeDto.getCode());

        return Code.builder()
                .codeKey(codeKey)
                .codeName(codeDto.getCodeName())
                .codeNameBrief(codeDto.getCodeNameBrief())
                .orderNo(codeDto.getOrderNo())
                .build();
    }
}
