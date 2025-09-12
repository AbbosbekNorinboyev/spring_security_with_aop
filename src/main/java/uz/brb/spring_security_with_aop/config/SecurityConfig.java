package uz.brb.spring_security_with_aop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.brb.spring_security_with_aop.dto.response.Response;

import java.time.LocalDateTime;

import static uz.brb.spring_security_with_aop.util.Util.localDateTimeFormatter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html",
                                "/api/auths/**"
                        ).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        // 401 Unauthorized
                        .authenticationEntryPoint((request, response, authException) -> {
                            Response<?> resp = Response.builder()
                                    .code(HttpStatus.UNAUTHORIZED.value())
                                    .status(HttpStatus.UNAUTHORIZED)
                                    .message("Unauthorized")
                                    .success(false)
                                    .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                                    .build();

                            response.setContentType("application/json");
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            new ObjectMapper().writeValue(response.getOutputStream(), resp);
                        })
                        // 403 Forbidden
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            Response<?> resp = Response.builder()
                                    .code(HttpStatus.FORBIDDEN.value())
                                    .status(HttpStatus.FORBIDDEN)
                                    .message("Forbidden")
                                    .success(false)
                                    .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                                    .build();

                            response.setContentType("application/json");
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            new ObjectMapper().writeValue(response.getOutputStream(), resp);
                        })
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}