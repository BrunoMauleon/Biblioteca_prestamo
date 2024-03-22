package mx.azurian.biblioteca.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.azurian.biblioteca.entity.Libro;
import mx.azurian.biblioteca.entity.Prestamo;
import mx.azurian.biblioteca.service.LibroService;
import mx.azurian.biblioteca.service.PrestamoService;
@Controller
@RequestMapping("/prestamo")
public class PrestamoController {
	@Autowired
	private PrestamoService prestamoService;
	@Autowired
	private LibroService libroService;
	@GetMapping( "/tabla")
	public String listar(Model modelo) {
		List<Prestamo> prestamo = prestamoService.findAll();
		
		 modelo.addAttribute("titulo","Lista de prestamos");
		modelo.addAttribute("prestamo", prestamo);
		return "inicio_prestamo";
	}

	@GetMapping("/nuevo")
	public String mostrar(Map<String,Object> modelo) {
		Prestamo prestamo = new Prestamo();
		modelo.put("prestamo", prestamo);
		modelo.put("titulo", "Registro de prestamos");
		return "nuevo_prestamo";
	}
	@PostMapping("/nuevo")
	public String guardar(@Validated Prestamo prestamo, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status) {
	    if (result.hasErrors()) {
	        modelo.addAttribute("titulo", "Registro de préstamos");
	        return "nuevo_prestamo";
	    }

	    Libro libroPrestado = prestamo.getLibro();
	    Libro libroActualizado = libroService.findOne(libroPrestado.getId_libro());
	    
	    if (libroActualizado.isDisponible()) {
	        // Permitir el préstamo
	        prestamoService.save(prestamo); // Guardar el préstamo
	        libroActualizado.setDisponible(false); // Marcar el libro como no disponible
	        libroService.save(libroActualizado); // Guardar el cambio en la disponibilidad del libro
	        status.setComplete();
	        flash.addFlashAttribute("success", "Préstamo registrado con éxito");
	        return "redirect:/prestamo/tabla";
	    } else {
	        flash.addFlashAttribute("error", "El libro no está disponible para préstamo en este momento.");
	        return "redirect:/prestamo/nuevo";
	    }
	}


	
	@GetMapping("/editar/{idPrestamo}")
	public String mostrarFormularioDeEditar(@PathVariable Long idPrestamo, Model modelo) {
		modelo.addAttribute("prestamos", prestamoService.findOne(idPrestamo));
		return "editar_prestamo.html";
	}
	@PostMapping("/editar/{idPrestamo}")
	public String actualizar(@PathVariable Long idPrestamo, Prestamo prestamo, Model modelo) {
	    Prestamo a = prestamoService.findOne(idPrestamo);
	    
	    a.setFechaPrestamo(prestamo.getFechaPrestamo());
	    
	    if (prestamo.getFecha_devolucion() != null) {
	        // Actualizar la fecha de devolución
	        a.setFecha_devolucion(prestamo.getFecha_devolucion());

	        // Obtener el libro asociado al préstamo
	        Libro libroPrestado = a.getLibro();

	        // Cambiar la disponibilidad del libro a true
	        libroPrestado.setDisponible(true);

	        // Guardar el libro con la disponibilidad actualizada
	        libroService.save(libroPrestado);
	    }
	    
	    prestamoService.save(a);
	    return "redirect:/prestamo/tabla";
	}

	
	@GetMapping("/eliminar/{idPrestamo}")
	public String eliminar(@PathVariable(value = "idPrestamo") Long id,RedirectAttributes flash) {
		if(id > 0) {
			prestamoService.delete(id);
			flash.addFlashAttribute("success", "dato eliminado con exito");
		}
		return "redirect:/prestamo/tabla";
	}
	
	
}
