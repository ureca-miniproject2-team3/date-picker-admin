package com.mycom.myapp.codes.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.codes.entity.CommonCodeResultDto;
import com.mycom.myapp.codes.service.CommonCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommonCodeController {

	private final CommonCodeService commonCodeService;
	
	@PostMapping("/commoncodes")
	public ResponseEntity<CommonCodeResultDto> getCommonCodeList(@RequestBody List<String> groupCodes) {
		CommonCodeResultDto commonCodeResultDto = commonCodeService.getCommonCodeList(groupCodes);
		
		if(Objects.equals(commonCodeResultDto.getResult(), "success")) {
			return ResponseEntity.ok(commonCodeResultDto);
		} 
		else {
			return ResponseEntity.internalServerError().body(commonCodeResultDto);
		}
	}
}
