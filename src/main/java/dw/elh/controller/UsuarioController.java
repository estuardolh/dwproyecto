package dw.elh.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dw.elh.dto.UsuarioDto;
import dw.elh.model.Usuario;
import dw.elh.service.UsuarioService;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioServicio;

	@RequestMapping(value="/registra",method = RequestMethod.POST)
	public String registra(@ModelAttribute("userDto") UsuarioDto usuarioDto
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes) {

		String usuario = usuarioDto.getUsuario();
		String clave = usuarioDto.getClave();
		String nombre = usuarioDto.getNombre();
		Usuario newUsuario = new Usuario();
		newUsuario.setUsuario(usuario);
		newUsuario.setClave(clave);
		newUsuario.setNombre(nombre);
		usuarioServicio.saveUsuario(newUsuario);
		return "redirect:panel";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@ModelAttribute("userDto") UsuarioDto usuario
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes) {
		if(!ObjectUtils.isEmpty(usuario.getUsuario())) {
			Optional<Usuario> optionalUsuario = usuarioServicio.getUsuario(usuario.getUsuario());
			if(optionalUsuario.isPresent() && usuarioServicio.login(usuario.getUsuario(), usuario.getClave())) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("login", "true");
				
				return "redirect:panel";
			}
		}
	   return "redirect:inicio";
	}
	
	@GetMapping("/panel")
	public String panel(ModelMap modelo
			, HttpServletRequest request
			, HttpServletRequest httpRequest) throws NoSuchAlgorithmException {
		HttpSession sesion = request.getSession();
		if(!ObjectUtils.isEmpty(sesion.getAttribute("login"))
				&& sesion.getAttribute("login").equals("true")) {
			modelo.addAttribute("usuarios", usuarioServicio.getUsuarios());
			return "panel";
		}
		else
			return "redirect:inicio";
	}
	
}
