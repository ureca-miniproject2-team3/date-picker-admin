package com.mycom.myapp.codes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mycom.myapp.codes.dto.GroupCodeDto;
import com.mycom.myapp.codes.entity.GroupCode;
import com.mycom.myapp.codes.entity.GroupCodeResultDto;
import com.mycom.myapp.codes.repository.GroupCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupCodeServiceImpl implements GroupCodeService {
	
	private final GroupCodeRepository groupCodeRepository;
	
	@Override
	public GroupCodeResultDto listGroupCode(int pageNumber, int pageSize) {
		GroupCodeResultDto groupCodeResultDto = new GroupCodeResultDto();
		
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			
			List<GroupCodeDto> groupCodeDtoList = 
					groupCodeRepository.findAll(pageable)
						.stream()
						.toList()
						.stream()
						.map(GroupCodeDto::fromGroupCode)
						.toList();

			Long count = groupCodeRepository.count();
			
			groupCodeResultDto.setGroupCodeDtoList(groupCodeDtoList);
			groupCodeResultDto.setCount(count);
			groupCodeResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			groupCodeResultDto.setResult("fail");
		}
		
		return groupCodeResultDto;
	}

	@Override
	public GroupCodeResultDto detailGroupCode(String groupCode) {
		GroupCodeResultDto groupCodeResultDto = new GroupCodeResultDto();
		
		try {
			Optional<GroupCode> optionalGroupCode = groupCodeRepository.findById(groupCode);
			
			optionalGroupCode.ifPresentOrElse(
					findGroupCode -> { 
						GroupCodeDto groupCodeDto = GroupCodeDto.fromGroupCode(findGroupCode);
						groupCodeResultDto.setGroupCodeDto(groupCodeDto);
						groupCodeResultDto.setResult("success");
					},
					() -> groupCodeResultDto.setResult("fail")
			);
			

		} catch (Exception e) {
			e.printStackTrace();
			groupCodeResultDto.setResult("fail");
		}
		
		return groupCodeResultDto;
	}

	@Override
	public GroupCodeResultDto insertGroupCode(GroupCodeDto groupCodeDto) {
		GroupCodeResultDto groupCodeResultDto = new GroupCodeResultDto();
		
		try {
			GroupCode groupCode =
					GroupCode.builder()
						.groupCode(groupCodeDto.getGroupCode())
						.groupCodeName(groupCodeDto.getGroupCodeName())
						.groupCodeDesc(groupCodeDto.getGroupCodeDesc())
						.isNew(true)
						.build();
				
			groupCodeRepository.save(groupCode);
			groupCodeResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			groupCodeResultDto.setResult("fail");
		}
		
		return groupCodeResultDto;
	}

	@Override
	public GroupCodeResultDto updateGroupCode(GroupCodeDto groupCodeDto) {
		GroupCodeResultDto groupCodeResultDto = new GroupCodeResultDto();
		
		try {
			GroupCode groupCode =
					GroupCode.builder()
						.groupCode(groupCodeDto.getGroupCode())
						.groupCodeName(groupCodeDto.getGroupCodeName())
						.groupCodeDesc(groupCodeDto.getGroupCodeDesc())
						.isNew(false)
						.build();
				
			groupCodeRepository.save(groupCode);
			groupCodeResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			groupCodeResultDto.setResult("fail");
		}
		
		return groupCodeResultDto;
	}

	@Override
	public GroupCodeResultDto deleteGroupCode(String groupCode) {
		GroupCodeResultDto groupCodeResultDto = new GroupCodeResultDto();
		
		try {
			groupCodeRepository.deleteById(groupCode);
			
			groupCodeResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			groupCodeResultDto.setResult("fail");
		}
		
		return groupCodeResultDto;
	}

	@Override
	public GroupCodeResultDto countGroupCode() {
		GroupCodeResultDto groupCodeResultDto = new GroupCodeResultDto();
		
		try {
			Long count = groupCodeRepository.count();
			
			groupCodeResultDto.setCount(count);
			groupCodeResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			groupCodeResultDto.setResult("fail");
		}
		
		return groupCodeResultDto;
	}

}
