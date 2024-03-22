package mx.azurian.biblioteca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.azurian.biblioteca.entity.Libro;


public interface LibroService {
	public List<Libro> findAll(boolean disponible);
	public List<Libro> obtenerTodosLosLibros();

	public Page<Libro> findAll(Pageable pageable);

	public void save(Libro libro);

	public Libro findOne(Long id_libro);

	public void delete(Long id_libro);
}
