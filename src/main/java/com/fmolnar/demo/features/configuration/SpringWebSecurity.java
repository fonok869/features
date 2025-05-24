package com.fmolnar.demo.features.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SpringWebSecurity {

    public static final String REALM_NAME = "Authentication required";
    public static final String KEY = "571b264a-6868-49e6-9e43-ce80a5749b8f";

    @Bean
    public SecurityFilterChain springSecurityFilterChain2(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/free/**").permitAll()
                        .requestMatchers("/rs/**").hasRole("WS").anyRequest().authenticated()
                ).addFilter(digestFilter())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(digestEntryPoint()));
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
//                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .exceptionHandling(e -> e.authenticationEntryPoint(digestEntryPoint()))
//                .addFilterBefore(digestFilter(), DigestAuthenticationFilter.class)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/rs/**").hasAnyRole("USER")
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // For Digest Authentification it is important to not use User.withDefaultPasswordEncoder()
        // because it adds brypt encryption and the password will never match!!!!
        manager.createUser(User.builder().username("user").password("userPass").roles("USER", "WS").build());
        manager.createUser(User.builder().username("admin").password("adminPass").roles("USER", "ADMIN", "WS").build());
        return manager;
    }


    @Bean
    public DigestAuthenticationFilter digestFilter() {
        DigestAuthenticationFilter result = new DigestAuthenticationFilter();
        result.setUserDetailsService(userDetailsService());
        result.setAuthenticationEntryPoint(digestEntryPoint());
        result.setCreateAuthenticatedToken(true);
        return result;
    }

    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
        entryPoint.setRealmName(REALM_NAME);
        entryPoint.setKey(KEY);
        return entryPoint;
    }
}
