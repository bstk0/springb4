package br.com.dbengine.springb4.security;

import br.com.dbengine.springb4.dbUtil.Sysout;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Component
public class MyAuthenticationManager implements AuthenticationManager {

    private String uname = "";
    private String upassw = "";
    private String existingUserName = "admin"; //"Aladdin";
    private String existingPassword = "2021";  //open sesame";

    public MyAuthenticationManager() {
        System.out.println(" >> MyAuthenticationManager ...");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(">> Authentication - 27 ... ");
        uname = String.valueOf(authentication.getName());
        upassw = String.valueOf(authentication.getCredentials());

        if (uname.equals(existingUserName) && upassw.equals(existingPassword)) {
            UsernamePasswordAuthenticationToken authenticationToken;
            authenticationToken = new UsernamePasswordAuthenticationToken(uname, null, getAuthorities());
            return authenticationToken;
        } else {
            return null;
        }
    }

    private List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = Arrays.asList("ADMIN", "USER");
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return authorities;
    }

}