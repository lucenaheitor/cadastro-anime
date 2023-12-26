package anime.heitor.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        return   http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.requestMatchers(HttpMethod.POST, "/login").permitAll()
                //.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .build();
    }

    @Bean  // Anotação do Spring que indica que o método retorna um bean que deve ser gerenciado pelo Spring.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{
        return  configuration.getAuthenticationManager();  // Retorna o AuthenticationManager fornecido pela configuração do Spring Boot.
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
    return   new BCryptPasswordEncoder();
    }
}
