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

import mx.azurian.biblioteca.entity.Usuario;
import mx.azurian.biblioteca.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping( "/tabla")
	public String listar(Model modelo) {
		List<Usuario> usuario = usuarioService.findAll();
		
		 modelo.addAttribute("titulo","Lista de usuarios");
		modelo.addAttribute("usuario", usuario);
		return "inicio_usuario";
	}

	@GetMapping("/nuevo")
	public String mostrar(Map<String,Object> modelo) {
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		modelo.put("titulo", "Registro de usuario");
		return "nuevo_usuario";
	}
	@PostMapping("/nuevo")
	public String guardarUsuario(@Validated Usuario usuario,BindingResult result,Model modelo,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			modelo.addAttribute("titulo1", "Registro de usuarios");
			return "nuevo_usuario";
		}
		
		String mensaje = (usuario.getIdUsuario() != null) ? "El usuario ha sido editato con exito" : "usuario registrado con exito";
		
		usuarioService.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/usuario/tabla";
	}
	
	@GetMapping("/editar/{idUsuario}")
	public String mostrarFormularioDeEditar(@PathVariable Long idUsuario, Model modelo) {
		modelo.addAttribute("usuarios", usuarioService.findOne(idUsuario));
		modelo.addAttribute("titulo", "Editar Usuario");
		return "editar_usuario";
	}
	@PostMapping("/editar/{idUsuario}")
	public String actualizar(@PathVariable Long idUsuario, Usuario usuario, Model modelo) {
		Usuario a = usuarioService.findOne(idUsuario);
		
		a.setNombre(usuario.getNombre());
		a.setApellido(usuario.getApellido());
		usuarioService.save(a);
		return "redirect:/usuario/tabla";
	}
	
	@GetMapping("/eliminar/{idUsuario}")
	public String eliminar(@PathVariable(value = "idUsuario") Long id,RedirectAttributes flash) {
		if(id > 0) {
			usuarioService.delete(id);
			flash.addFlashAttribute("success", "usuario eliminado con exito");
		}
		return "redirect:/usuario/tabla";
	}
	
}
