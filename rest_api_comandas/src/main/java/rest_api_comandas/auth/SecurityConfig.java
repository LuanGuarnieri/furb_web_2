package rest_api_comandas.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint entryPoint;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, JwtAuthenticationEntryPoint entryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.entryPoint = entryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // libera tudo só para testar
            )
            .exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .formLogin(form -> form.disable())
            .build();
    }
}
