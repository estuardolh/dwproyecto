package dw.elh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dw.elh.model.Herramienta;
import dw.elh.service.HerramientaService;
import dw.elh.service.MenuService;

@Controller
@RequestMapping(value="/herramientas")
public class HerramientaController extends BaseController {
	@Autowired
	HerramientaService herramientaServicio;
	@Autowired
	MenuService menuServicio;
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String listar(ModelMap modelo
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes) {
		
		if(loggedIn(request)) {
			preparaModel(modelo);
			List<Herramienta> listaHerramienta = herramientaServicio.getHerramientas();
			modelo.addAttribute("herramientas", listaHerramienta);
			
			return "herramientaListar";
		}else {
			return "redirect:/";
		}
	}
}
