package com.mycom.myapp.codes.entity;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
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
