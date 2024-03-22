package mx.azurian.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.azurian.biblioteca.entity.Prestamo;
import mx.azurian.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService{
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findAll() {
		return (List<Prestamo>) prestamoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Prestamo> findAll(Pageable pageable) {
		return prestamoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Prestamo prestamo) {
		prestamoRepository.save(prestamo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		prestamoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Prestamo findOne(Long id) {
		return prestamoRepository.findById(id).orElse(null);
	}
}
