package com.raven.munin.model.request.member;

import com.raven.munin.model.request.base.Request;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class MemberReq extends Request {
    private Integer seq;
    private String id;
    private String name;
    private String code;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
