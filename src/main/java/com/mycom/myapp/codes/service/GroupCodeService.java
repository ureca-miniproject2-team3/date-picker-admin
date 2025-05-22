package com.mycom.myapp.codes.service;

import com.mycom.myapp.codes.dto.GroupCodeDto;
import com.mycom.myapp.codes.entity.GroupCodeResultDto;

public interface GroupCodeService {

	GroupCodeResultDto listGroupCode(int pageNumber, int pageSize);
	
	GroupCodeResultDto detailGroupCode(String groupCode);
	
	GroupCodeResultDto insertGroupCode(GroupCodeDto groupCodeDto);
	
	GroupCodeResultDto updateGroupCode(GroupCodeDto groupCodeDto);
	
	GroupCodeResultDto deleteGroupCode(String groupCode);
	
	GroupCodeResultDto countGroupCode();
	
}
