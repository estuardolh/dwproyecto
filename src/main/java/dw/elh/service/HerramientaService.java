package dw.elh.service;

import java.util.List;

import dw.elh.model.Herramienta;

public interface HerramientaService {
	void save(Herramienta herramienta);
	void delete(Long id);
	List<Herramienta> getHerramientas();
}
