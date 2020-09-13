package dw.elh.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dw.elh.dto.MenuDto;
import dw.elh.service.UsuarioService;

@Controller
@RequestMapping(value="/panel")
public class PanelController {
	@Autowired
	UsuarioService usuarioServicio;

	@GetMapping("/")
	public String panel(ModelMap modelo
			, HttpServletRequest request
			, HttpServletRequest httpRequest) throws NoSuchAlgorithmException {
		HttpSession sesion = request.getSession();
		if(!ObjectUtils.isEmpty(sesion.getAttribute("login"))
				&& sesion.getAttribute("login").equals("true")) {
			MenuDto menuUsuarioAgregar = new MenuDto();
			menuUsuarioAgregar.setNombre("Agregar");
			menuUsuarioAgregar.setEnlace("/usuario/agregarUsuario");
			
			MenuDto menuUsuarioListar = new MenuDto();
			menuUsuarioListar.setNombre("Listar");
			menuUsuarioAgregar.setEnlace("/usuario/listarUsuario");
			
			MenuDto menuUsuario = new MenuDto();
			menuUsuario.setNombre("Usuario");
			menuUsuario.setSubMenus(Arrays.asList(menuUsuarioAgregar, menuUsuarioListar));
			
			modelo.addAttribute("menus", Arrays.asList(menuUsuario));
			return "panel";
		}
		else
			return "redirect:inicio";
	}
}
