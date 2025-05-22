package com.mycom.myapp.codes.entity;

import java.util.List;

import com.mycom.myapp.codes.dto.GroupCodeDto;

import lombok.Data;

@Data
public class GroupCodeResultDto {

	private String result;
	
	private GroupCodeDto groupCodeDto;
	
	private List<GroupCodeDto> groupCodeDtoList;
	
	private Long count;
	
}
