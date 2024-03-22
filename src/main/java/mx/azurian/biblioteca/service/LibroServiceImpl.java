package mx.azurian.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.azurian.biblioteca.entity.Libro;
import mx.azurian.biblioteca.repository.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService{

	@Autowired
	private LibroRepository libroRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAll(boolean disponible) {
        return libroRepository.findByDisponible(disponible);
    }
	public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> findAll(Pageable pageable) {
		return libroRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Libro libro) {
		libroRepository.save(libro);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		libroRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findOne(Long id) {
		return libroRepository.findById(id).orElse(null);
	}
}
