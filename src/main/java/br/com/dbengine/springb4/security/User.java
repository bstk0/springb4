package br.com.dbengine.springb4.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
public class User {
    private String username; //userName;
    private String password; //userPassword;
    private List<GrantedAuthority> userAuthorities;

    public User(String username, String password, List<GrantedAuthority> userAuthorities) {
        this.username = username;
        this.password = password;
        this.userAuthorities = userAuthorities;
    }
}
