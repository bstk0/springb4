package br.com.dbengine.springb4.security;

import br.com.dbengine.springb4.dbUtil.Sysout;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UsersRepo {

    private List<User> usersList = new ArrayList<>();

    private void setInitialUsersList() {
        Sysout.s(" >> Iniciando lista de usuarios ...");
        usersList.add(new User("rodrigo", "123456", getAuthorities(new ArrayList<>( Arrays.asList( new String[]{"ROLE_ADMIN", "ROLE_USER"})))));
        usersList.add(new User("user1", "user1_passw", getAuthorities(new ArrayList<String>( Arrays.asList( new String[]{"ROLE_USER"})))));
        usersList.add(new User("user2", "user2_passw", getAuthorities(new ArrayList<String>( Arrays.asList( new String[]{"ROLE_USER"})))));
        usersList.add(new User("admin", "2021", getAuthorities(new ArrayList<String>( Arrays.asList( new String[]{"ROLE_ADMIN"})))));
    }


    public UsersRepo() {
        if (this.usersList.isEmpty()) {
            this.setInitialUsersList();
        }
    }

    public List<User> getUsers() {
        return usersList;
    }

    public User getUserByName(String name) {

        try {
            return usersList.stream().filter(user -> user.getUsername().equals(name))
                    .findFirst().orElseThrow(() -> new RuntimeException("Username Not Found!"));
        } catch (Exception e) {
            Sysout.s(" ERRO >> " + e.getMessage());
            return null;
        }
    }


    public boolean isUserPasswordValid(String username, String password) {

        User user = getUserByName(username);
        return (user.getPassword().equals(password));

    }

    private List<GrantedAuthority> getAuthorities(List<String> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return authorities;

    }

}
