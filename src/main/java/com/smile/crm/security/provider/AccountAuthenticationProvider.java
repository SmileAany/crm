package com.smile.crm.security.provider;

import com.smile.crm.security.token.AccountAuthenticationToken;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author smile
 * @Notes AccountAuthenticationProvider
 * @Notes 参考
 * @date 2022/4/22
 * @time 3:02 PM
 * @example
 * @link
 */
@Data
public class AccountAuthenticationProvider implements AuthenticationProvider {
    /***
     * 加密规则
     **/
    private PasswordEncoder passwordEncoder;

    /***
     * UserDetailsService
     **/
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountAuthenticationToken authenticationToken = (AccountAuthenticationToken) authentication;

        UserDetails userDetails = userDetailsService
                .loadUserByUsername((String)authenticationToken.getPrincipal());

        if (passwordEncoder.matches(authentication.getCredentials().toString(),userDetails.getPassword())){
            AccountAuthenticationToken authenticationResult =
                    new AccountAuthenticationToken(userDetails,userDetails.getAuthorities());
            authenticationResult.setDetails(authenticationToken.getDetails());

            return authenticationResult;
        }

        throw new BadCredentialsException("密码异常");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AccountAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
