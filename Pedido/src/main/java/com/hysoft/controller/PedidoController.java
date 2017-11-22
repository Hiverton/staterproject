package com.hysoft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hysoft.model.Pedido;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	private static List<Pedido> LISTA_PEDIDOS = new ArrayList<Pedido>();


	@RequestMapping(method = RequestMethod.GET) 
	public ModelAndView listar(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ListaPedidos");
		LISTA_PEDIDOS = new ArrayList<Pedido>();
		
		for (int i = 0; i < 2; i++) {
			Pedido pedido = new Pedido();
			pedido.setIdContrato(i*50);
			pedido.setNumPeriodo(1);
			pedido.setPrimeiroAno(200+i);
			LISTA_PEDIDOS.add(pedido);
		}

		Pedido novoPedido = new Pedido();
		mv.addObject("pedido", novoPedido);
		
		mv.addObject("listaPedido", LISTA_PEDIDOS);
		
		request.setAttribute("transportadora", true);
		
		return mv;
	}
	
	@RequestMapping(value="/cadastro/novo", method = RequestMethod.POST)
	public ModelAndView cadastro(Pedido pedido, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("CadastroPedido");
		mv.addObject("pedido", pedido);
		return mv;
	}
	
	@RequestMapping(value="/cadastro/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("CadastroPedido");
		
		Pedido pedido = new Pedido();
		pedido.setIdContrato(id);
		pedido.setNumPeriodo(1);
		pedido.setPrimeiroAno(100);
		
		mv.addObject("pedido", pedido);
		
		return mv;
	}
	
	@RequestMapping(value="/cadastro", method = RequestMethod.POST)
	public String salvar(Pedido pedido) {
		LISTA_PEDIDOS.add(pedido);
		return "redirect:/pedidos";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvarLista(HttpServletRequest request) {

		for (Pedido pedido : LISTA_PEDIDOS) {
			System.out.println(" valor => " + request.getParameter("alunos_novos" + pedido.getIdContrato()));
		}
		
		return "redirect:/pedidos";
	}
	
	@RequestMapping(value="/saveajax", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> salvarListaAJAX(HttpServletRequest request) {

		StringBuffer output = new StringBuffer("{\"LISTA_PEDIDOS\":[");
		
		
		int i=0;
		for (Pedido pedido : LISTA_PEDIDOS) {
			i=1;
			output.append("{\"ID_CONTRATO\":\"" + pedido.getIdContrato() + "\",");
			output.append("\"NUM_PERIODO\":\"" + pedido.getNumPeriodo() + "\",");
			output.append("\"TIPO_PEDIDO\":\"" + pedido.getIndTipoPedido() + "\"},");
		}
		
		if(i > 0){
			output.replace(output.length()-1, output.length(), "]");
		}
		
		if(i == 0){
			output.append("]");
		}

		output.append("}");
		
		return new ResponseEntity<Object>(output.toString(), HttpStatus.OK);
	}
	
}
