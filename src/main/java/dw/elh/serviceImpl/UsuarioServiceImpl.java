package dw.elh.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dw.elh.model.Usuario;
import dw.elh.repository.UsuarioRepository;
import dw.elh.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public boolean login(String nombreUsuario, String pass) {
		Optional<Usuario> optionalUsuario = usuarioRepository.getUsuarioByUsuario(nombreUsuario);
		if(optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			if(usuario.getClave().equals(pass)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Optional<Usuario> getUsuario(String nombreUsuario) {
		Optional<Usuario> usuario = usuarioRepository.getUsuarioByUsuario(nombreUsuario);
		return usuario;
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public List<Usuario> getUsuarios(){
		return (List<Usuario>) usuarioRepository.findAll();
	}
}
