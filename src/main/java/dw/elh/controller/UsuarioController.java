package dw.elh.controller;

import java.util.List;
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
import dw.elh.model.Menu;
import dw.elh.model.Perfil;
import dw.elh.model.Usuario;
import dw.elh.service.PerfilService;
import dw.elh.service.UsuarioService;

@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController extends BaseController {
	@Autowired
	UsuarioService usuarioServicio;
	@Autowired
	PerfilService perfilServicio;

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
				String colorBarra = (usuarioDto.getColorBarra() == null || usuarioDto.getColorBarra().isEmpty())? "#007bff": usuarioDto.getColorBarra();
				String colorFondo = (usuarioDto.getColorFondo() == null || usuarioDto.getColorFondo().isEmpty())? "#FFFFFF": usuarioDto.getColorFondo();
				String colorLetra = (usuarioDto.getColorLetra() == null || usuarioDto.getColorLetra().isEmpty())? "#000000": usuarioDto.getColorLetra();
				String perfilIdString = usuarioDto.getPerfilId();
				
				Optional<Perfil> perfil = perfilServicio.getPerfilById(Long.parseLong(perfilIdString));
				
				Menu menu = null;
				
				Usuario newUsuario = new Usuario();
				newUsuario.setUsuario(usuario);
				newUsuario.setClave(clave);
				newUsuario.setNombre(nombre);
				newUsuario.setMenu(menu);
				newUsuario.setColorBarra(colorBarra);
				newUsuario.setColorFondo(colorFondo);
				newUsuario.setColorLetra(colorLetra);
				
				if(perfil.isPresent()) {
					newUsuario.setPerfil(perfil.get());
				}
				
				usuarioServicio.saveUsuario(newUsuario);
				
				return "redirect:/usuarios/";
			}else if(request.getMethod().equals(HttpMethod.GET.toString())) {
				List<Perfil> perfiles = perfilServicio.getPerfiles();
				modelo.addAttribute("perfiles", perfiles);
			}
		}else {
			return "redirect:/";
		}		
		
		return "usuarioAgregar";
	}
	
	@RequestMapping(value="/editar",method = {RequestMethod.GET, RequestMethod.POST})
	public String editar(@ModelAttribute("userDto") UsuarioDto usuarioDto
			, @RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(loggedIn(request)) {
			preparaModel(modelo);
			
			if(request.getMethod().equals(HttpMethod.POST.toString())) {
				String usuario = usuarioDto.getUsuario();
				
				Optional<Usuario> usuarioOpt = usuarioServicio.getUsuario(usuario);
				if(usuarioOpt.isPresent()) {
					String nombre = usuarioDto.getNombre();					
					String clave = usuarioDto.getClave();
					
					String colorBarra = (usuarioDto.getColorBarra() == null || usuarioDto.getColorBarra().isEmpty())? "#007bff": usuarioDto.getColorBarra();
					String colorFondo = (usuarioDto.getColorFondo() == null || usuarioDto.getColorFondo().isEmpty())? "#FFFFFF": usuarioDto.getColorFondo();
					String colorLetra = (usuarioDto.getColorLetra() == null || usuarioDto.getColorLetra().isEmpty())? "#000000": usuarioDto.getColorLetra();
					
					Usuario usuarioToUpdate = usuarioOpt.get();
					usuarioToUpdate.setNombre(nombre);
					usuarioToUpdate.setClave(clave);
					
					usuarioToUpdate.setColorBarra(colorBarra);
					usuarioToUpdate.setColorFondo(colorFondo);
					usuarioToUpdate.setColorLetra(colorLetra);
					
					HttpSession sesion = request.getSession(false);
					sesion.setAttribute("usuario", usuarioToUpdate);
					
					usuarioServicio.saveUsuario(usuarioToUpdate);
					
					return "redirect:/usuarios/";
				}else {
					return "usuarioEditar";
				}
			}else {
				modelo.addAttribute("usuario", usuarioServicio.getUsuarios());
			}
		}else {
			return "redirect:/";
		}
		
		return "usuarioEditar";
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

	@RequestMapping(value="/eliminar",method = {RequestMethod.POST})
	public String elimina(@RequestHeader HttpHeaders httpHeaders
			, HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, ModelMap modelo) {
		if(loggedIn(request)) {
			String eliminarId = request.getParameter("eliminaId");
			
			usuarioServicio.delete(Long.valueOf(eliminarId));
			
			return "redirect:/usuarios/";
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
				Usuario elUsuario = optionalUsuario.get(); 
				HttpSession sesion = request.getSession();
				sesion.setAttribute("login", "true");
				sesion.setAttribute("usuario", elUsuario);
				
				sesion.setAttribute("barra_color", elUsuario.getColorBarra());
				sesion.setAttribute("fondo_color", elUsuario.getColorFondo());
				sesion.setAttribute("letra_color", elUsuario.getColorLetra());
				
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
