package com.mycom.myapp.codes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.codes.entity.GroupCode;

public interface GroupCodeRepository extends JpaRepository<GroupCode, String> {

}
