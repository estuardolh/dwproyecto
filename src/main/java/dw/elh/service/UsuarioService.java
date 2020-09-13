package dw.elh.service;

import java.util.List;
import java.util.Optional;

import dw.elh.model.Usuario;

public interface UsuarioService {
	boolean login(String nombre, String pass);
	Optional<Usuario> getUsuario(String nombreUsuario);
	void saveUsuario(Usuario usuario);
	List<Usuario> getUsuarios();
}
