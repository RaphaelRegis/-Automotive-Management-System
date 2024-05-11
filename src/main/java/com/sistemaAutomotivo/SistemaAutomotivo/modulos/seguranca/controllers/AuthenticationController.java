package com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.dto.AuthenticationDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.dto.LoginResponseDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.dto.RegisterDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.entities.Usuario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.repositories.UsuarioRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.services.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
	private TokenService tokenService;



    @GetMapping()
    public String obter() {
        return "foi algo";
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO data) {

        if (usuarioRepository.existsByNomeUsuario(data.userName())) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario novoUsuario = new Usuario(null, data.userName(), encryptedPassword, data.role());
        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.userName(), data.password());
		var auth = this.authenticationManager.authenticate(userNamePassword);
        
		var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
