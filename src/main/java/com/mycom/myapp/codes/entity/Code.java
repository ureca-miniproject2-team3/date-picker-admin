package com.mycom.myapp.codes.entity;

import com.mycom.myapp.codes.entity.key.CodeKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Code {

    @EmbeddedId
    CodeKey codeKey;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_name_brief")
    private String codeNameBrief;

    @Column(name = "order_no")
    private Integer orderNo;
}
