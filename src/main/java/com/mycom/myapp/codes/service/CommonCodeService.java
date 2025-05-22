package com.mycom.myapp.codes.service;

import java.util.List;

import com.mycom.myapp.codes.entity.CommonCodeResultDto;

public interface CommonCodeService {

	CommonCodeResultDto getCommonCodeList(List<String> groupCodes);
	
}
