package dw.elh.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dw.elh.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
	Optional<Usuario> getUsuarioByNombre(String nombre);
}
