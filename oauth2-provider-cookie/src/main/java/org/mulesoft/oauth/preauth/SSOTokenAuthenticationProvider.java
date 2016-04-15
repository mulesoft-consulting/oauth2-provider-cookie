package org.mulesoft.oauth.preauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SSOTokenAuthenticationProvider implements AuthenticationProvider {
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        if (name.equalsIgnoreCase("sso-user-token")) {
        	List<GrantedAuthority> grantedAuths = new ArrayList();
        	grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        	Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        	System.out.println("SSO Token: " + password);
        	return auth;
        }
        else {
        	return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}