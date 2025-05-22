package com.mycom.myapp.codes.repository;

import com.mycom.myapp.codes.entity.Code;
import com.mycom.myapp.codes.entity.key.CodeKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodeRepository extends JpaRepository<Code, CodeKey> {

    @Query("select c from Code c where c.codeKey.groupCode = :groupCode order by c.orderNo")
    Page<Code> findByGroupCode(@Param("groupCode") String groupCode, Pageable pageable);
}
