package com.mycom.myapp.codes.service;

import com.mycom.myapp.codes.dto.CodeDto;
import com.mycom.myapp.codes.dto.CodeResultDto;
import com.mycom.myapp.codes.entity.Code;
import com.mycom.myapp.codes.entity.key.CodeKey;

public interface CodeService {

    CodeResultDto insertCode(CodeDto codeDto);
    CodeResultDto updateCode(CodeDto codeDto);
    CodeResultDto deleteCode(CodeKey codeKey);

    CodeResultDto listCode(String groupCode, int pageNumber, int pageSize);
    CodeResultDto detailCode(CodeKey codeKey);
    CodeResultDto countCode();
}
