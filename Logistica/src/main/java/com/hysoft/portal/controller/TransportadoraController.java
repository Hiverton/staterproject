package com.hysoft.portal.controller;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/transportadoras")
public class TransportadoraController {

	private static final Set<String> LISTA_TRANSPORTADORAS = new TreeSet<String>();
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("Transportadoras");
		
		LISTA_TRANSPORTADORAS.add("MERIDIONAL");
		LISTA_TRANSPORTADORAS.add("BRASPREZ");
		
		mv.addObject("listaTransportadoras", LISTA_TRANSPORTADORAS);
		
		return mv;
	}
	
}
