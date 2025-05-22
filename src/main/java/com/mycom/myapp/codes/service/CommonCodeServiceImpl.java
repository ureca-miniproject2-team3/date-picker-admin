package com.mycom.myapp.codes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.mycom.myapp.codes.dto.CodeDto;
import com.mycom.myapp.codes.entity.Code;
import com.mycom.myapp.codes.entity.CommonCodeResultDto;
import com.mycom.myapp.codes.repository.CommonCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService {

	private final CommonCodeRepository commonCodeRepository;
	
	@Override
	public CommonCodeResultDto getCommonCodeList(List<String> groupCodes) {
		CommonCodeResultDto commonCodeResultDto = new CommonCodeResultDto();
		
		try {
			List<Code> codeList = commonCodeRepository.findByGroupCodes(groupCodes);
			Map<String, List<CodeDto>> commonCodeListMap = new HashMap<>();
			
			String curGroupCode = "";
			List<CodeDto> codeDtoList = null;
			
			for(Code code : codeList) {
				String groupCode = code.getCodeKey().getGroupCode();
				
				if(!Objects.equals(curGroupCode, groupCode)) {
					if(Strings.isNotEmpty(curGroupCode)) 
						commonCodeListMap.put(curGroupCode, codeDtoList);
					
					curGroupCode = groupCode;
					codeDtoList = new ArrayList<>();
				}
				
				codeDtoList.add(CodeDto.fromCode(code));
			}
			
			commonCodeListMap.put(curGroupCode, codeDtoList);
			
			commonCodeResultDto.setCommonCodeDtoListMap(commonCodeListMap);
			commonCodeResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			commonCodeResultDto.setResult("fail");
		}
		
		return commonCodeResultDto;
	}

}
