package mx.azurian.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.azurian.biblioteca.entity.Usuario;
import mx.azurian.biblioteca.repository.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
}
