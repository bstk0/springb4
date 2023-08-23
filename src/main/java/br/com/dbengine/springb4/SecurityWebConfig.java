package br.com.dbengine.springb4;

//import org.springframework.beans.factory.annotation.Autowired;
import br.com.dbengine.springb4.dbUtil.Sysout;
import br.com.dbengine.springb4.security.CustomAuthenticationProvider;
import br.com.dbengine.springb4.security.MyAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@EnableWebSecurity
@Configuration
public class SecurityWebConfig {

    public static final String[] ENDPOINTS_WHITELIST = {
            "/resources/**",
            "/webapp/**",
            "/webjars/**",
            "/login",
            "/process-login",
            "/styles"
    };

    //@Autowired
    //private CustomAuthenticationProvider authProvider;

    //@Autowired
    //private MyAuthenticationManager authManager;
    private MyAuthenticationManager authManager = new MyAuthenticationManager();

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authProvider);
//        return authenticationManagerBuilder.build();
//    }

//    private AuthenticationManager authManager() throws Exception {
//        //AuthenticationManager authManager = new ProviderManager(new MyFirstCustomAuthenticationProvider());
//        Sysout.s(">> AuthenticationManager authManager() ");
//        AuthenticationManager aManager = new ProviderManager(authProvider);
//        return aManager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("rodrigo")
//                .password(passwordEncoder().encode("12345"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("12345"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder
//                .inMemoryAuthentication()
//                .withUser("bisterco").password("1234")
//                .roles("USER")
//                .and()
//                .withUser("zico").password("1234")
//                .roles("USER");
//    }
    //@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    //.antMatchers("/resources/**").permitAll()
//                    .antMatchers("/login").permitAll()
//                    .antMatchers("/**").hasAnyRole("USER","ADMIN")
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/loginPage")
//                    .loginProcessingUrl("/process-login")
//                    .defaultSuccessUrl("/")
//                    .usernameParameter("username").passwordParameter("password")
//                    .failureUrl("/login?error=true")
//                    //.permitAll()
//                    .and()
//                .logout()
//                    .permitAll();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/login").permitAll()
                .antMatchers(ENDPOINTS_WHITELIST).permitAll()
                //.antMatchers("/users/**", "/settings/**").hasAuthority("Admin")
                //.hasAnyAuthority("Admin", "Editor", "Salesperson")
                //.hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/process-login")
//                    .defaultSuccessUrl("/")
//                    .usernameParameter("username").passwordParameter("password")
//                    .failureUrl("/login?error=true")
                //.permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
                .and()
                .logout().permitAll();

        http.headers().frameOptions().sameOrigin();
        //http.addFilter(new BasicAuthenticationFilter(authManager()));
        http.addFilter(new BasicAuthenticationFilter(authManager));
        return http.build();
    }
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("123"));
//    }

    //@Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/resources/**", "/static/**");
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        Sysout.s(" >> Passou em WebSecurityCustomizer ...");
        return (web) -> web.ignoring().antMatchers("/resources/**", "/static/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}