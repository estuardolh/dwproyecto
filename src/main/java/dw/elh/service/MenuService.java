package dw.elh.service;

import java.util.List;

import dw.elh.dto.MenuDto;
import dw.elh.model.Menu;

public interface MenuService {
	List<MenuDto> getMenu();
	void save(Menu menu);
	List<Menu> getMenusPadre();
	List<MenuDto> getMenusListOrderedByPadre();
	void delete(Long id);
	Menu getMenuById(Long id);
}
