package com.mycom.myapp.codes.dto;

import java.util.List;
import lombok.Data;

@Data
public class CodeResultDto {

    private String result;
    private CodeDto codeDto;
    private List<CodeDto> codeDtoList;
    private Long count;
}
