package br.com.e_commerce.demo.infra.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracoesSeguranca {

    private final FiltroTokenAcesso filtroTokenAcesso;

    public ConfiguracoesSeguranca(FiltroTokenAcesso filtroTokenAcesso) {
        this.filtroTokenAcesso = filtroTokenAcesso;
    }

    @Bean
    public SecurityFilterChain filtrosDeSeguranca(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(req -> {
                req.requestMatchers(HttpMethod.POST, "/usuario/cadastro").permitAll();
                req.requestMatchers(HttpMethod.POST, "/login").permitAll();

                req.requestMatchers(HttpMethod.DELETE, "/usuario/**").hasRole("ADMINISTRADOR");
                req.requestMatchers(HttpMethod.PATCH, "/usuario/**").hasRole("ADMINISTRADOR");

                req.requestMatchers("/carrinho").hasRole("CLIENTE");
                req.requestMatchers("/carrinho/**").hasRole("CLIENTE");

                req.requestMatchers("/compra/**").hasRole("CLIENTE");

                req.anyRequest().authenticated();
            })
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(filtroTokenAcesso, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder encriptador() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RoleHierarchy hierarquiaPerfil() {
        String hierarquia = "ROLE_ADMINISTRADOR > ROLE_MODERADOR\n" +
        "ROLE_MODERADOR > ROLE_CLIENTE";
        return RoleHierarchyImpl.fromHierarchy(hierarquia);
    }
}
