package dw.elh.service;

import java.util.List;
import java.util.Optional;

import dw.elh.model.Perfil;

public interface PerfilService {
	void save(Perfil perfil);
	void delete(Long id);
	List<Perfil> getPerfiles();
	Optional<Perfil> getPerfilById(Long perfilId);
}
