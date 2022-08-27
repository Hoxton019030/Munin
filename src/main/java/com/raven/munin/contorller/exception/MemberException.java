package com.raven.munin.contorller.exception;

import lombok.Data;

@Data
public class MemberException extends BaseException{
// TODO: 2022/8/27 這邊可以再擴充
    
    public MemberException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
    public MemberException(String errorMsg) {
        super(errorMsg);
    }



    public static MemberException createAPIException(String errorMsg) {
        return new MemberException(errorMsg);
    }



}
