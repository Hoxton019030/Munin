package com.raven.munin.model.response.member;

import com.raven.munin.model.entity.Member;
import com.raven.munin.model.response.base.ResBody;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberRes extends ResBody {

    private Member member;

    public MemberRes() {
    }

    public MemberRes(Member member) {
        this.member = member;
    }
}
