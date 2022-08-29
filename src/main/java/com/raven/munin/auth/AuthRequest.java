package com.raven.munin.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 裡面放帳號密碼的成員屬性
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
