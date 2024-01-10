package br.com.dbengine.springb4.security;

//import org.springframework.beans.factory.annotation.Autowired;
import br.com.dbengine.springb4.security.CustomAuthenticationProvider;
import br.com.dbengine.springb4.security.MyAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@EnableWebSecurity
@Configuration
//@EnableWebSecurity
public class SecurityWebConfig {
    public static final String[] ENDPOINTS_WHITELIST = {
            "/resources/**",
            "/webapp/**",
            "/webjars/**",
            "/login",
            "/process-login",
            "/styles"
    };
    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println(">> Inicio filterChain ..");
        http.authorizeRequests()
                //.antMatchers("/login").permitAll()
                .antMatchers(ENDPOINTS_WHITELIST).permitAll()
                //.antMatchers("/users/**", "/settings/**").hasAuthority("Admin")
                //.hasAnyAuthority("Admin", "Editor", "Salesperson")
                //.hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // TODO: Login customizado esta dando erro qdo entra sem / no final da URL.
                // Quando for corrigir, descomentar linhas abaixo
//                    .loginPage("/login")
//                    .loginProcessingUrl("/process-login")
//                    .defaultSuccessUrl("/")
//                    .usernameParameter("username").passwordParameter("password")
//                    .failureUrl("/login?error=true")
                //.permitAll()
                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
//                .and()
                .logout().permitAll();

        http.headers().frameOptions().sameOrigin();
        //http.addFilter(new BasicAuthenticationFilter(authManager()));
        http.addFilter(new BasicAuthenticationFilter(new MyAuthenticationManager()));
        //http.addFilter(new CustomAuthenticationFilter(authManager.getOrBuild());
        System.out.println(">> FIM filterChain ..");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        System.out.println(" >> Passou em WebSecurityCustomizer ...");
        return (web) -> web.ignoring().antMatchers("/webapp/**",
                "/resources/**",
                "/WEB-INF/**");
        // "/static/**");
        //return (web) -> web.ignoring().antMatchers(ENDPOINTS_WHITELIST);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }
}