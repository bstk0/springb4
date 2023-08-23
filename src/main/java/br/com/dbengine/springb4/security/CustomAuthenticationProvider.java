package br.com.dbengine.springb4.security;

import br.com.dbengine.springb4.dbUtil.Sysout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    //private final Log logger = LogFactory.getLog(getClass());


    @Autowired
    private CustomUserDetailsService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Sysout.s(">> Authenticate - 24 ...");
        UsernamePasswordAuthenticationToken authToken = null;

        if (authentication == null) {
            return null;
        }
        String uname = String.valueOf(authentication.getName());
        String upassw = String.valueOf(authentication.getCredentials());

        //logger.info("Username: " + uname + " Password: " + upassw);
        Sysout.s("Username: " + uname + " Password: " + upassw);

        //User is an org.springframework.security.core.userdetails.User object
        User user = (User) userService.loadUserByUsername(uname);

        if (user == null) return null; //throw new UsernameNotFoundException(String.format("Username not found"));

        if (user.getPassword().equals(upassw)) {
            authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getUserAuthorities());
        }
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
