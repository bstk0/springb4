package br.com.dbengine.springb4.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    public Collection<Object> getRoles() {
        //return null;
        ArrayList<Object> objects = new ArrayList<>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_USER"}));
        return objects;
    }
}
