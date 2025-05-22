package com.mycom.myapp.codes.entity;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCode implements Persistable<String> {

	@Id
	private String groupCode;
	
	private String groupCodeName;
	
	private String groupCodeDesc;
	
	private transient boolean isNew = false;

	@Override
	public String getId() {
		return null;
	}
}
