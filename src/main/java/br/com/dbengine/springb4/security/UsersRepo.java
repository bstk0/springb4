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

    private static List<User> usersList = new ArrayList<>();

    public UsersRepo() {
        if (this.usersList.isEmpty()) {
            Sysout.s(" %%%%%%%%%%%%%%%%%%%%%%% UsersRepo %%%%%%%%%%%%%%%%%%%%%%%%%%%%% ");
        }
    }


    public void setInicialUserList(String usr, String pwd, String[] roles) {
        Sysout.s(" >> Iniciando lista de usuarios by parameters ... 2");
        //Sysout.s( "PWD:" + pwd);
        //TODO: Ajustar roles no security prop
        usersList.add(new User(usr, pwd, getAuthorities(new ArrayList<String>( Arrays.asList( roles )))));
    }

//    public UsersRepo() {
//        if (this.usersList.isEmpty()) {
//            this.setInitialUsersList();
//        }
//    }

    public List<User> getUsers() {
        return usersList;
    }

    public User getUserByName(String name) {
        try {
            Sysout.s(String.valueOf(usersList.size()));
            return usersList.stream().filter(user -> user.getUsername().equals(name))
                    .findFirst().orElseThrow(() -> new RuntimeException("Username Not Found!"));
        } catch (Exception e) {
            System.out.println(" ERRO >> " + e.getMessage());
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
