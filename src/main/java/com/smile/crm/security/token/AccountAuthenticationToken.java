package com.smile.crm.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author smile
 * @Notes account 重写token
 * @date 2022/4/22
 * @time 3:04 PM
 * @example
 * @link
 */
public class AccountAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 550L;

    /***
     * 邮箱
     **/
    private final Object principal;

    /***
     * 密码
     **/
    private Object credentials;

    /***
     * 构造
     * 账户和密码
     **/
    public AccountAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    /***
     * 构造
     * 账户和密码
     * 权限
     **/
    public AccountAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
