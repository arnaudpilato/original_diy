package com.wildcodeschool.original_diy.config;

import com.wildcodeschool.original_diy.service.DiyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Pil : Added the annotation @EnableGlobalMethodSecurity <br>
 * - The securedEnabled property determines if the @Secured annotation should be enabled <br>
 * The @Secured annotation is used to specify a list of roles on a method <br>
 * - The jsr250Enabled property allows us to use the @RoleAllowed annotation <br>
 * The @RoleAllowed annotation is the JSR-250’s equivalent annotation of the @Secured annotation <br>
 * - The prePostEnabled property enables Spring Security pre/post annotations <br>
 * The @PreAuthorize annotation checks the given expression before entering the method,
 * whereas the @PostAuthorize annotation verifies it after the execution of the method and could alter the result
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DiyUserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizeHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    /**
     * Pil : We need UserDetailsService for implement the user details to perform authetication and authorization
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Pil : Authentication of principal with true of false
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Pil : We also need a PasswordEncoder for the DaoAuthenticationProvider.
     * If we don't specify, it will use plaint text
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Pil : Implementation of the possibility to use the cross-origin <br>
     * - Disable the csrf for use JWT <br>
     * - SessionCreationPolicy.STATELESS will ensure no session is created by spring security <br>
     * - When we want the user to be authenticated we need to use the filter authenticationJwtTokenFilter
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizeHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
