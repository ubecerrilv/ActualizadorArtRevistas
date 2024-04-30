package com.act.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/buscar")
public class ControladorHTML {
	//Objeto para poder regresar el HTML
	@Autowired
	private RecuperadorHTML recuperador;
	private AutomataRevistas autoRevistas = new AutomataRevistas();
	private HashSet<String> revistas = new HashSet<String>();
	
	@GetMapping()
	public String obtenerHTML(Model model) {
		//Temporal
		String url = "https://revistas.unam.mx/index.php/";

		//Obtener html de la pagina y pasarlo para obtener las revistas
		String html = recuperador.obtenerHTML(url);
		//Retirar el protocolo
		revistas = autoRevistas.revistas(html, url.substring(url.indexOf("//")+2));

		//Agregar revistas al model para verlos en el html
		model.addAttribute("revistas", revistas);

		for (String revista : revistas){
			System.out.println(revista);
		}


		return "Mostrar";
	}
}
