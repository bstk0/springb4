package br.com.dbengine.springb4;

import br.com.dbengine.springb4.dbUtil.Sysout;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProviderOld implements AuthenticationProvider {

    UserDetails isValidUser(String username, String password) {
        if (username.equalsIgnoreCase("rodrigo")
                && password.equals("20212021")) {

            UserDetails user = User
                    .withUsername(username)
                    .password("NOT_DISCLOSED")
                    .roles("USER_ROLE")
                    .build();

            return user;
        }
        return null;
    }
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Sysout.s(" >> CustomAuthenticationProvider ...");

        UserDetails userDetails = isValidUser(username, password);

        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Incorrect user credentials !!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
