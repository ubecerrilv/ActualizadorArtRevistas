package com.act.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class ControladorHTML {
	//Objeto para poder regresar el HTML
	@Autowired
	private RecuperadorHTML recuperador;
	@Autowired
	private ValidadorHTML validador;
	private AutomataRevistas autoRevistas = new AutomataRevistas();
	private AutomataArticulos autoArticulos = new AutomataArticulos();
	private HashSet<String> revistas = new HashSet<String>();
	//private ArrayList<String> articulos = new ArrayList<String>();

	
	@GetMapping("/buscar")
	//@ResponseBody
	public String/*Revista*/ obtenerHTML(Model model) {
		//Temporal
		String url = "https://revistas.unam.mx/index.php/";

		//Obtener html de la pagina y pasarlo para obtener las revistas
		String html = recuperador.obtenerHTML(url);
		//Retirar el protocolo
		revistas = autoRevistas.revistas(html, url.substring(url.indexOf("//")+2));
		revistas.add("http://publicaciones.anuies.mx/revista");

		//Agregar revistas al model para verlos en el html
		model.addAttribute("revistas", revistas);

		int j =1;
		for (String revista : revistas){
			String add = ": NO OJS";
			if(validador.esOJS(revista)) add = ": OJS";
			System.out.println(j+"."+revista+add);
			String html2 = recuperador.obtenerHTML(revista);

			ArrayList<String> articulos = new ArrayList<String>();
			articulos = autoArticulos.articulos(html2, revista);

			for(int i = 1; i<articulos.size();i++){
				System.out.println("\t"+i+". "+articulos.get(i));
			}
			j++;
			articulos.clear();
		}
		return "Mostrar";//new Revista(urlA, articulos);

	}
}
