package dw.elh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dw.elh.dto.PerfilDto;
import dw.elh.model.Perfil;
import dw.elh.service.MenuService;
import dw.elh.service.PerfilService;

@Controller
@RequestMapping(value="/perfiles")
public class PerfilController extends BaseController {
	@Autowired
	PerfilService perfilServicio;
	@Autowired
	MenuService menuServicio;

	@RequestMapping(value="/agregar",method = {RequestMethod.GET, RequestMethod.POST})
	public String registra(@ModelAttribute("perfilDto") PerfilDto perfilDto
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(loggedIn(request)) {
			preparaModel(modelo);
			
			if(request.getMethod().equals(HttpMethod.POST.toString())) {
				Perfil perfil = new Perfil();
				perfil.setNombre(perfilDto.getNombre());
				perfilServicio.save(perfil);
				
				return "redirect:/perfiles/";
			}
		}else {
			return "redirect:/";
		}
		
		return "perfilAgregar";
	}
	
	@RequestMapping(value="/eliminar",method = {RequestMethod.POST})
	public String elimina(@RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(loggedIn(request)) {
			String eliminarId = request.getParameter("eliminaId");
			
			perfilServicio.delete(Long.valueOf(eliminarId));
			
			return "redirect:/perfiles/";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String listar(ModelMap modelo
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes) {
		
		if(loggedIn(request)) {
			preparaModel(modelo);
			List<Perfil> listaPerfil = perfilServicio.getPerfiles();
			modelo.addAttribute("perfiles", listaPerfil);
			
			return "perfilListar";
		}else {
			return "redirect:/";
		}
	}
}
