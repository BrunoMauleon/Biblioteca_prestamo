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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import mx.azurian.biblioteca.entity.Libro;
import mx.azurian.biblioteca.service.LibroService;




@Controller
@RequestMapping("/libro")
public class LibroController {
	@Autowired
	private LibroService libroService;
	
	
	
	
	
	@GetMapping( "/tabla")
	public String listar(Model modelo, @RequestParam(name = "disponible", required = false) String disponible) {
	    List<Libro> libro;
	    if (disponible == null || disponible.isEmpty()) {
	        libro = libroService.obtenerTodosLosLibros();
	    } else {
	        boolean disponibilidad = Boolean.parseBoolean(disponible);
	        libro = libroService.findAll(disponibilidad);
	    }
	    modelo.addAttribute("titulo","Lista de libros");
	    modelo.addAttribute("libro", libro);
	    return "inicio.html";
	}

	@GetMapping("/nuevo")
	public String mostrar(Map<String,Object> modelo) {
		Libro libro = new Libro();
		modelo.put("libro", libro);
		modelo.put("titulo", "Registro de libros");
		return "nuevo_libro";
	}
	@PostMapping("/nuevo")
	public String guardarLibro(@Validated Libro libro,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			return "nuevo_libro";
		}
		
		String mensaje = (libro.getId_libro() != null) ? "El libro ha sido editato con exito" : "libro registrado con exito";
		libro.setDisponible(true);
		libroService.save(libro);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/libro/tabla";
	}
	
	@GetMapping("/editar/{id_libro}")
	public String mostrarFormularioDeEditar(@PathVariable Long id_libro, Model modelo) {
		modelo.addAttribute("libros", libroService.findOne(id_libro));
		modelo.addAttribute("titulo", "Editar Libro");
		return "editar.html";
	}
	@PostMapping("/editar/{id_libro}")
	public String actualizar(@PathVariable Long id_libro, Libro libro, Model modelo) {
		Libro a = libroService.findOne(id_libro);
		
		a.setTitulo(libro.getTitulo());
		a.setAutor(libro.getAutor());
		a.setTipo(libro.getTipo());
		a.setSinopsis(libro.getSinopsis());
		a.setNoPaginas(libro.getNoPaginas());
		libroService.save(a);
		return "redirect:/libro/tabla";
	}
	
	@GetMapping("/eliminar/{id_libro}")
	public String eliminar(@PathVariable(value = "id_libro") Long id,RedirectAttributes flash) {
		if(id > 0) {
			libroService.delete(id);
			flash.addFlashAttribute("success", "Libro eliminado con exito");
		}
		return "redirect:/libro/tabla";
	}
	
	
}
