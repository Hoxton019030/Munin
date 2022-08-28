package com.raven.munin.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 裡面放帳號密碼的成員屬性
 */
@Data
public class AuthRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
