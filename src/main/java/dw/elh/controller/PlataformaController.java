package dw.elh.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class PlataformaController {
	@Autowired
	UsuarioService usuarioServicio;
	
	@RequestMapping(value="/registra",method = RequestMethod.POST)
	public String registra(@ModelAttribute("userDto") UsuarioDto usuario
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes) {
		usuarioServicio.saveUsuario(new Usuario(usuario));
		return "redirect:panel";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@ModelAttribute("userDto") UsuarioDto usuario
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes) {
       /*httpHeaders.forEach((headerName, HeaderValue) -> {
           System.out.println("Header Name:"+headerName + ", Header Value:"+HeaderValue);
       });*/
		if(!ObjectUtils.isEmpty(usuario.getNombreUsuario())) {
			Optional<Usuario> optionalUsuario = usuarioServicio.getUsuario(usuario.getNombreUsuario());
			if(optionalUsuario.isPresent() && usuarioServicio.login(usuario.getNombreUsuario(), usuario.getClave())) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("login", "true");
				
				return "redirect:panel";
			}
		}
	   return "inicio";
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
			return "inicio";
	}
	
	@GetMapping("/")
	public String inicio(//@RequestParam(name="nombreUsuario", required=false) String nombreUsuario
			//, @RequestParam(name="clave", required=false) String clave
			Model modelo
			, HttpServletRequest httpRequest) throws NoSuchAlgorithmException {
		
		return "inicio";
	}
}