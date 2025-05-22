package com.mycom.myapp.codes.controller;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.codes.dto.GroupCodeDto;
import com.mycom.myapp.codes.entity.GroupCodeResultDto;
import com.mycom.myapp.codes.service.GroupCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "GroupCode", description = "그룹코드 관리 API")
public class GroupCodeController {

	private final GroupCodeService groupCodeService;
	
	@GetMapping("/groupcodes")
	@Operation(summary = "그룹코드 리스트 조회", description = "페이지 번호, 페이지 크기에 맞는 그룹코드 리스트를 조회합니다.")
	public ResponseEntity<GroupCodeResultDto> listGroupCode(
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize
	) {
		GroupCodeResultDto groupCodeResultDto = groupCodeService.listGroupCode(pageNumber, pageSize);
		
		if(Objects.equals(groupCodeResultDto.getResult(), "success")) 
			return ResponseEntity.ok(groupCodeResultDto);
		else
			return ResponseEntity.internalServerError().body(groupCodeResultDto);
	}
	
	@GetMapping("/groupcodes/{groupCode}")
	@Operation(summary = "그룹코드 상세 조회", description = "그룹코드(ID) 값에 맞는 그룹코드를 상세 조회합니다.")
	public ResponseEntity<GroupCodeResultDto> detailGroupCode(@PathVariable("groupCode") String groupCode) {
		GroupCodeResultDto groupCodeResultDto = groupCodeService.detailGroupCode(groupCode);
		
		if(Objects.equals(groupCodeResultDto.getResult(), "success")) 
			return ResponseEntity.ok(groupCodeResultDto);
		else
			return ResponseEntity.internalServerError().body(groupCodeResultDto);
	}
	
	@PostMapping("/groupcodes")
	@Operation(summary = "그룹코드 생성", description = "그룹코드를 생성합니다.")
	public ResponseEntity<GroupCodeResultDto> insertGroupCode(GroupCodeDto groupCodeDto) {
		GroupCodeResultDto groupCodeResultDto = groupCodeService.insertGroupCode(groupCodeDto);
		
		if(Objects.equals(groupCodeResultDto.getResult(), "success")) 
			return ResponseEntity.ok(groupCodeResultDto);
		else
			return ResponseEntity.internalServerError().body(groupCodeResultDto);
	}
	
	@PutMapping("/groupcodes/{groupCode}")
	@Operation(summary = "그룹코드 변경", description = "그룹코드를 변경합니다.")
	public ResponseEntity<GroupCodeResultDto> updateGroupCode(
			@PathVariable("groupCode") String groupCode,
			GroupCodeDto groupCodeDto
	) {
		groupCodeDto.setGroupCode(groupCode);
		
		GroupCodeResultDto groupCodeResultDto = groupCodeService.updateGroupCode(groupCodeDto);
		
		if(Objects.equals(groupCodeResultDto.getResult(), "success")) 
			return ResponseEntity.ok(groupCodeResultDto);
		else
			return ResponseEntity.internalServerError().body(groupCodeResultDto);
	}
	
	@DeleteMapping("/groupcodes/{groupCode}")
	@Operation(summary = "그룹코드 삭제", description = "그룹코드를 삭제합니다.")
	public ResponseEntity<GroupCodeResultDto> deleteGroupCode(@PathVariable("groupCode") String groupCode) {
		GroupCodeResultDto groupCodeResultDto = groupCodeService.deleteGroupCode(groupCode);
		
		if(Objects.equals(groupCodeResultDto.getResult(), "success")) 
			return ResponseEntity.ok(groupCodeResultDto);
		else
			return ResponseEntity.internalServerError().body(groupCodeResultDto);
	}
	
	@GetMapping("/groupcodes/count")
	@Operation(summary = "그룹코드 개수", description = "그룹코드의 개수를 반환합니다.")
	public ResponseEntity<GroupCodeResultDto> countGroupCode() {
		GroupCodeResultDto groupCodeResultDto = groupCodeService.countGroupCode();
		
		if(Objects.equals(groupCodeResultDto.getResult(), "success")) 
			return ResponseEntity.ok(groupCodeResultDto);
		else
			return ResponseEntity.internalServerError().body(groupCodeResultDto);
	}

}
