package com.act.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class ControladorHTML {
	//Objeto para poder regresar el HTML
	@Autowired
	private RecuperadorHTML recuperador;
	private AutomataRevistas autoRevistas = new AutomataRevistas();
	private AutomataArticulos autoArticulos = new AutomataArticulos();
	private HashSet<String> revistas = new HashSet<String>();
	private HashSet<String> articulos = new HashSet<String>();

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@GetMapping("/buscar")
	@ResponseBody
	public Revista obtenerHTML(Model model) {
		//Temporal
		String url = "https://revistas.unam.mx/index.php/";

		//Obtener html de la pagina y pasarlo para obtener las revistas
		String html = recuperador.obtenerHTML(url);
		//Retirar el protocolo
		revistas = autoRevistas.revistas(html, url.substring(url.indexOf("//")+2));

		//Agregar revistas al model para verlos en el html
		model.addAttribute("revistas", revistas);

		/*for (String revista : revistas){
			System.out.println(revista);
		}*/

		int i =1, j=1;
		/*for (String revista : revistas){
			System.out.println(j+"."+revista);
			String html2 = recuperador.obtenerHTML(revista);
			articulos = autoArticulos.articulos(html2, revista);
			for(String art: articulos){
				System.out.println("\t"+i+". "+art);
				i++;
			}
			j++;
		}*/
		String urlA = "https://revistas.unam.mx/index.php/aca";
		String html2 = recuperador.obtenerHTML(urlA);
		articulos = autoArticulos.articulos(html2, urlA);

		/*for(String art: articulos){
			System.out.println("\t"+i+". "+art);
				i++;
		}*/

		return new Revista(urlA, articulos);

	}
}
