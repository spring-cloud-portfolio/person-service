package com.doroshenko.serhey.person.web.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration(proxyBeanMethods = false)
public class OAuth2Config {

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password("{noop}password1").roles("USER").build(),
                User.withUsername("user2").password("{noop}password2").roles("USER").build(),
                User.withUsername("user3").password("{noop}password3").roles("USER").build()
        );
    }

}
