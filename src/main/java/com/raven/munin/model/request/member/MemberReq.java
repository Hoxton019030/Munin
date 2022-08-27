package com.raven.munin.model.request.member;

import com.raven.munin.enumeration.MemberAuthority;
import com.raven.munin.model.request.base.Request;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
public class MemberReq extends Request {

    // TODO: 2022/8/27 可以補上正則表達式的限制 @Pattern
    @NotBlank(message = "不能為空")
    @Length(message = "太長了，不可以超過15個字", max = 15)
    private String id;
    // TODO: 2022/8/27 可以補上正則表達式的限制 @Pattern
    @NotBlank(message = "不能為空")
    private String name;
    @NotBlank(message = "不能為空")
    private String password;

    private MemberAuthority authority;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
