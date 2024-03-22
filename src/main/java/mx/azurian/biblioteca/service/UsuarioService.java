package mx.azurian.biblioteca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.azurian.biblioteca.entity.Usuario;

public interface UsuarioService {
	public List<Usuario> findAll();

	public Page<Usuario> findAll(Pageable pageable);

	public void save(Usuario usuario);

	public Usuario findOne(Long idUsuario);

	public void delete(Long idUsuario);
}
