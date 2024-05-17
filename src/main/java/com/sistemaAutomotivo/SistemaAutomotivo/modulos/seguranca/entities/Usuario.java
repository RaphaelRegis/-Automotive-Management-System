package com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.entities.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;
    
    private String nomeUsuario;
    private String senhaUsuario;
    private Role roleUsuario;

    // metodos da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // caso o roleUsuario seja ADMIN
        if (this.roleUsuario == Role.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("FUNCIONARIO"), new SimpleGrantedAuthority("CLIENTE"));

        // caso o roleUsuario seja FUNCIONARIO
        } else if (this.roleUsuario == Role.FUNCIONARIO) {
            return List.of(new SimpleGrantedAuthority("FUNCIONARIO"), new SimpleGrantedAuthority("CLIENTE"));

        // caso o roleUsuario seja CLIENTE
        } else {
            return List.of(new SimpleGrantedAuthority("CLIENTE"));
        }
    }

    @Override
    public String getPassword() {
        return senhaUsuario;
    }

    @Override
    public String getUsername() {
        return nomeUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
