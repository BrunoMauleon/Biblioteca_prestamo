package mx.azurian.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.azurian.biblioteca.entity.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long>{
	List<Libro> findByDisponible(boolean disponible);
}
