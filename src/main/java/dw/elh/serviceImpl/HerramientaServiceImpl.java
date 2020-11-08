package dw.elh.serviceImpl;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dw.elh.model.Herramienta;
import dw.elh.repository.HerramientaRepository;
import dw.elh.service.HerramientaService;

@Service
public class HerramientaServiceImpl implements HerramientaService {
	@Autowired
	HerramientaRepository herramientaRepository;

	@Override
	public void save(Herramienta perfil) {
		herramientaRepository.save(perfil);
	}

	@Override
	public void delete(Long id) {
		herramientaRepository.deleteById(id);
	}

	@Override
	public List<Herramienta> getHerramientas() {
		return (List<Herramienta>) herramientaRepository.findAll();
	}
}
