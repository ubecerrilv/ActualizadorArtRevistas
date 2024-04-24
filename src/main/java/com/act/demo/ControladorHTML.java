package com.act.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/buscar")
public class ControladorHTML {
	//Objeto para poder regresar el HTML
	@Autowired
	private RecuperadorHTML recuperador;
	
	@GetMapping()
	public String obtenerHTML() {
		String url = "https://revistaidentidad.uaemex.mx/index.php/";
		System.out.println(url);
		System.out.print(recuperador.obtenerHTML(url));

		return "Holi.html";
	}
}
