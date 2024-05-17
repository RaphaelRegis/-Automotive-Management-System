package com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
	private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                // REQUISICOES ABERTAS PARA TODOS
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // REQUISICOES DO ADMIN
                        // modulo funcionarios
                        .requestMatchers(HttpMethod.POST, "/api/funcionarios").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/funcionarios/{cpfFuncionario}").hasAuthority("ADMIN")
                        // modulo equipes
                        .requestMatchers(HttpMethod.POST, "/api/equipes").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/equipes").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/equipes/adicionar/{nomeEquipe}/{cpfFuncionario}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/equipes/remover/{nomeEquipe}/{cpfFuncionario}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/equipes/{nomeEquipe}").hasAuthority("ADMIN")
                        // modulo produtos
                        .requestMatchers(HttpMethod.POST, "/api/produtos").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/produtos/{nome}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/produtos/{nome}").hasAuthority("ADMIN")
                        // modulo servicos
                        .requestMatchers(HttpMethod.POST, "/api/servicos").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/servicos/{nome}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/servicos/{nome}").hasAuthority("ADMIN")
                        // modulo orcamentos
                        .requestMatchers(HttpMethod.GET, "/api/orcamentos").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/orcamentos/{idOrcamento}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/orcamentos/{idOrcamento}").hasAuthority("ADMIN")
                        // REQUISICOES DO FUNCIONARIO
                        //modulo funcionarios
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/{cpfFuncionario}").hasAuthority("FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/equipes/{cpfFuncionario}").hasAuthority("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PATCH, "/api/funcionarios/{cpfFuncionario}").hasAuthority("FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/{cpfFuncionario}").hasAuthority("FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/equipes/{cpfFuncionario}").hasAuthority("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PATCH, "/api/funcionarios/{cpfFuncionario}").hasAuthority("FUNCIONARIO")
                        // modulo equipes
                        .requestMatchers(HttpMethod.GET, "/api/equipes/{nomeEquipe}").hasAuthority("FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET, "/api/equipes/membros/{nomeEquipe}").hasAuthority("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PATCH, "/api/equipes/{nomeEquipe}").hasAuthority("FUNCIONARIO")
                        // modulo clientes
                        .requestMatchers(HttpMethod.GET, "/api/clientes").hasAuthority("FUNCIONARIO")
                        // modulo veiculos
                        .requestMatchers(HttpMethod.GET, "/api/veiculos").hasAuthority("FUNCIONARIO")
                        // REQUISICOES DO CLIENTE
                        // modulo clientes
                        .requestMatchers(HttpMethod.POST, "/api/clientes").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/clientes/{cpfCnpj}").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/clientes/veiculos/{cpfCnpj}").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.PATCH, "/api/clientes/{cpfCnpj}").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/clientes/{cpfCnpj}").hasAuthority("CLIENTE")
                        // modulo veiculos
                        .requestMatchers(HttpMethod.POST, "/api/veiculos").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/veiculos/{placa}").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.PATCH, "/api/veiculos/{placa}").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/veiculos/{placa}").hasAuthority("CLIENTE")
                        // modulo orcamentos
                        .requestMatchers(HttpMethod.POST, "/api/orcamentos").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/orcamentos/totalOrcamento/{idOrcamento}").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.PATCH, "/api/orcamentos/orcarProduto").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.PATCH, "/api/orcamentos/orcarServico").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/orcamentos/{idOrcamento}").hasAuthority("CLIENTE")
                        // modulo produtos
                        .requestMatchers(HttpMethod.GET, "/api/produtos/").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/produtos/{nome}").hasAuthority("CLIENTE")
                        // modulo servicos
                        .requestMatchers(HttpMethod.GET, "/api/servicos/").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/servicos/{nome}").hasAuthority("CLIENTE")
                        // REQUISICOES DA DOCUMENTACAO
                        .requestMatchers("/v3/api-docs", "/v3/api-docs/**","/swagger-ui/**").permitAll()
                        // QUALQUER OUTRA REQUISICAO
                        .anyRequest().authenticated())
                        .csrf(AbstractHttpConfigurer::disable)
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
