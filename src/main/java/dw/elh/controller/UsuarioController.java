package dw.elh.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
@RequestMapping(value="/usuarios")
public class UsuarioController extends BaseController {
	@Autowired
	UsuarioService usuarioServicio;

	@RequestMapping(value="/agregar",method = {RequestMethod.GET, RequestMethod.POST})
	public String registra(@ModelAttribute("userDto") UsuarioDto usuarioDto
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(loggedIn(request)) {
			preparaModel(modelo);
			
			if(request.getMethod().equals(HttpMethod.POST.toString())) {
				String usuario = usuarioDto.getUsuario();
				String clave = usuarioDto.getClave();
				String nombre = usuarioDto.getNombre();
				Usuario newUsuario = new Usuario();
				newUsuario.setUsuario(usuario);
				newUsuario.setClave(clave);
				newUsuario.setNombre(nombre);
				usuarioServicio.saveUsuario(newUsuario);
				
				return "redirect:/usuarios/";
			}
		}else {
			return "redirect:/";
		}
		
		return "usuarioAgregar";
	}
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String listar(ModelMap modelo
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes) {
		
		if(loggedIn(request)) {
			preparaModel(modelo);
			modelo.addAttribute("usuarios", usuarioServicio.getUsuarios());
			return "usuarioListar";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@ModelAttribute("userDto") UsuarioDto usuario
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(!ObjectUtils.isEmpty(usuario.getUsuario())) {
			Optional<Usuario> optionalUsuario = usuarioServicio.getUsuario(usuario.getUsuario());
			if(optionalUsuario.isPresent() && usuarioServicio.login(usuario.getUsuario(), usuario.getClave())) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("login", "true");
				
				return "redirect:/panel/";
			}
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String inicio(ModelMap modelo
			, HttpServletRequest request) {
		logout(request);		
		return "redirect:/";
	}
}
