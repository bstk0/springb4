package br.com.dbengine.springb4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user;
        org.springframework.security.core.userdetails.User springUser = null;

        user = repo.getUserByName(username);

        if (user != null) {

            springUser = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getUserAuthorities());
            return springUser;
        } else {
            //throw new UsernameNotFoundException(String.format("Username not found"));
            return null;

        }
        //return null;
    }
}