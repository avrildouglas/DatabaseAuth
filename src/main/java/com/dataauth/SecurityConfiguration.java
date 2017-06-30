package com.dataauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
      		auth.userDetailsService(userDetailsServiceBean());
       /** auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");*/
    }

    
    @Autowired private UserRepository userRepository;
    
    @Override
    public UserDetailsService userDetailsServiceBean() throws
    Exception {
    		return new SSUserDetailsService(userRepository);
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    	throws Exception {
    	auth.userDetailsService(userDetailsServiceBean());
    }
    
}