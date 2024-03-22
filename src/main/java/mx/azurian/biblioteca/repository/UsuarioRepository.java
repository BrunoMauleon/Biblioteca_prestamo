package mx.azurian.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.azurian.biblioteca.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
