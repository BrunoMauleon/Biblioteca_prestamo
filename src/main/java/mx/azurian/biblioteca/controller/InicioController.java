package mx.azurian.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class InicioController {
	@GetMapping("")
	public String principal(){
	    return "presentacion.html";
	}
}
