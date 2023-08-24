package br.com.dbengine.springb4.security;

import br.com.dbengine.springb4.dbUtil.Sysout;
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

        Sysout.s(" >> loadUserByUsername .." + username);
        User user;
        //org.springframework.security.core.userdetails.User springUser = null;

        user = repo.getUserByName(username);

        CustomUserDetail userDetail=null;
        if(user != null){
            Sysout.s(" >> user nao nulo ");
            CustomUserDetail userDetails=new CustomUserDetail();
            userDetails.setUser(user);
            return userDetails;
        }
        throw new
                UsernameNotFoundException("User not exist with username :" + username);
        }
}