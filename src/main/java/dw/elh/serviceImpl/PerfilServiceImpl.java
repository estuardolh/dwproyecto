package dw.elh.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dw.elh.model.Perfil;
import dw.elh.repository.PerfilRepository;
import dw.elh.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {
	@Autowired
	PerfilRepository perfilRepository;

	@Override
	public void save(Perfil perfil) {
		perfilRepository.save(perfil);
	}

	@Override
	public void delete(Long id) {
		perfilRepository.deleteById(id);
	}

	@Override
	public List<Perfil> getPerfiles() {
		return (List<Perfil>) perfilRepository.findAll();
	}

	@Override
	public Optional<Perfil> getPerfilById(Long perfilId) {
		return perfilRepository.findById(perfilId);
	}
}
