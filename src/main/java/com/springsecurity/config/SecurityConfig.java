package com.springsecurity.config;

import com.springsecurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }



//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails user = User.withUsername("sameer")
//                .password(encoder.encode("root"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(encoder.encode("root"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }


    @Bean
    public CustomUserDetailsService userDetailsService() {

        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> {
//                            try {
//                                auth
//                                        .requestMatchers("/home/public").permitAll()
//                                        .requestMatchers("/home/signup").permitAll()
//                                        .requestMatchers("/home/signin").permitAll()
//                                        .requestMatchers("/home/user").permitAll()
//                                        .anyRequest().authenticated();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                ).authenticationProvider(authenticationProvider());

        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/home/public").permitAll()
                .requestMatchers("/home/signup").permitAll()
                .requestMatchers("/home/signin").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();

        return http.build();
    }
}
