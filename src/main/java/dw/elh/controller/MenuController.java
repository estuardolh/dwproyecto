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

import dw.elh.dto.MenuDto;
import dw.elh.model.Menu;
import dw.elh.service.UsuarioService;

@Controller
@RequestMapping(value="/menus")
public class MenuController extends BaseController {
	@Autowired
	UsuarioService usuarioServicio;

	@RequestMapping(value="/agregar",method = {RequestMethod.GET, RequestMethod.POST})
	public String registra(@ModelAttribute("menuDto") MenuDto menuDto
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(loggedIn(request)) {
			preparaModel(modelo);
			modelo.addAttribute("padres",menuService.getMenusPadre());
			
			if(request.getMethod().equals(HttpMethod.POST.toString())) {
				String nombre = menuDto.getNombre();
				String enlace = menuDto.getEnlace();
				String padreId = menuDto.getPadreId();
				
				Menu menuPadre = new Menu();
				menuPadre.setId(Long.valueOf(padreId));
				
				Menu menu = new Menu();
				menu.setNombre(nombre);
				menu.setArchivoHtml(enlace);
				menu.setMenuPadre(menuPadre);

				menuService.save(menu);
				
				return "redirect:/menus/";
			}
		}else {
			return "redirect:/";
		}
		
		return "menuAgregar";
	}
	
	@RequestMapping(value="/eliminar",method = {RequestMethod.POST})
	public String elimina(@RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(loggedIn(request)) {
			String eliminarId = request.getParameter("eliminaId");
			
			menuService.delete(Long.valueOf(eliminarId));
			
			return "redirect:/menus/";
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
			List<MenuDto> listaMenu = menuService.getMenusListOrderedByPadre();
			modelo.addAttribute("menuExtendido", listaMenu);
			
			return "menuListar";
		}else {
			return "redirect:/";
		}
	}
}
