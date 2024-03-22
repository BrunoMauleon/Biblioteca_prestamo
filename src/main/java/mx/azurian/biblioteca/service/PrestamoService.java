package mx.azurian.biblioteca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.azurian.biblioteca.entity.Prestamo;

public interface PrestamoService {
	public List<Prestamo> findAll();

	public Page<Prestamo> findAll(Pageable pageable);

	public void save(Prestamo prestamo);

	public Prestamo findOne(Long idPrestamo);

	public void delete(Long idPrestamo);
}
