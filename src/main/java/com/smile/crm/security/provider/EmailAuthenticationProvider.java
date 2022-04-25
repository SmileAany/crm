package com.smile.crm.security.provider;

import com.smile.crm.security.token.EmailAuthenticationToken;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author smile
 * @Notes EmailAuthenticationProvider
 * @date 2022/4/21
 * @time 6:19 PM
 * @example
 * @link
 */
@Data
public class EmailAuthenticationProvider implements AuthenticationProvider {
    /***
     * UserDetailsService
     **/
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthenticationToken authenticationToken = (EmailAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService
                .loadUserByUsername((String)authenticationToken.getPrincipal());

        EmailAuthenticationToken authenticationResult =
                new EmailAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
