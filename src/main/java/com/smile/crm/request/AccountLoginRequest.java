package com.smile.crm.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smile
 * @Notes 账号登陆参数
 * @date 2022/4/22
 * @time 12:13 PM
 * @example
 * @link
 */
@Setter
@Getter
public class AccountLoginRequest {
    private String username;

    private String password;
}
