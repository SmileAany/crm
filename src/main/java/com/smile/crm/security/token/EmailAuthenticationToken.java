package com.smile.crm.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author smile
 * @Notes email 重写token
 * @date 2022/4/21
 * @time 5:31 PM
 * @example
 * @link
 */
public class EmailAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 550L;

    /***
     * 邮箱
     **/
    private final Object principal;

    /***
     * 构造
     * principal 邮箱
     **/
    public EmailAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        this.setAuthenticated(false);
    }

    /***
     * 构造
     * principal 邮箱
     * authorities 权限
     **/
    public EmailAuthenticationToken(Object principal,
                                    Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
