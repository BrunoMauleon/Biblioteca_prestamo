package mx.azurian.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.azurian.biblioteca.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
