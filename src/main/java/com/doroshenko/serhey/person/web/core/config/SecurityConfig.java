package com.doroshenko.serhey.person.web.core.config;

import com.doroshenko.serhey.person.core.constant.ProfileConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.doroshenko.serhey.person.core.constant.ProfileConstants.PRODUCTION;
import static org.springframework.boot.autoconfigure.security.StaticResourceLocation.*;

/**
 * Configuration for web security filters.
 *
 * @author Serhey Doroshenko
 * @see WebSecurityConfigurerAdapter
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // Disable cors and csrf
        http.cors().and().csrf().disable();
        // Disable formLogin, httpBasic and logout
        http.formLogin().and().httpBasic().disable();
        // OAUTH2 client
//        http.oauth2Client(Customizer.withDefaults());
        // OAUTH2 resource server
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        // Session management
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Configure requests security
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.OPTIONS).permitAll()
                .mvcMatchers("/actuator/health").permitAll()
                .mvcMatchers(HttpMethod.POST, "/registration/sign-up.json").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(final WebSecurity web) {
        final String[] activeProfiles = env.getActiveProfiles();
        if (activeProfiles.length > 0 && PRODUCTION.equals(activeProfiles[0])) return;
        web.ignoring()
                .mvcMatchers("/v3/**", "/swagger-ui/**", "/swagger-ui.html")
                .requestMatchers(PathRequest.toStaticResources().at(Set.of(CSS, IMAGES, FAVICON)));
    }

    @Autowired
    public SecurityConfig(final Environment env) {
        this.env = env;
    }

}
