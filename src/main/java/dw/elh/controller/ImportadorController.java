package dw.elh.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dw.elh.service.UsuarioService;

@Controller
@RequestMapping(value="/importador")
public class ImportadorController extends BaseController {
	@Autowired
	UsuarioService usuarioServicio;

	@RequestMapping(value = {"/herramienta",""}, method = RequestMethod.GET)
	public String panel(ModelMap modelo
			, HttpServletRequest request
			, HttpServletRequest httpRequest) throws NoSuchAlgorithmException {
		HttpSession sesion = request.getSession();
		if(!ObjectUtils.isEmpty(sesion.getAttribute("login"))
				&& sesion.getAttribute("login").equals("true")) {
			
			modelo.addAttribute("menus", menuService.getMenu());
			return "herramientaImportar";
		}
		else
			return "redirect:/";
	}
}
