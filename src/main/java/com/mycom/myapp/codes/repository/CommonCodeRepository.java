package com.mycom.myapp.codes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycom.myapp.codes.entity.Code;
import com.mycom.myapp.codes.entity.key.CodeKey;

public interface CommonCodeRepository extends JpaRepository<Code, CodeKey>{

	@Query("select c from Code c where c.id.groupCode in :groupCodes order by c.id.groupCode, c.orderNo")
	List<Code> findByGroupCodes(@Param("groupCodes") List<String> groupCodes);
}
