package dw.elh.service;

import java.util.List;

import dw.elh.dto.MenuDto;
import dw.elh.model.Menu;

public interface MenuService {
	List<MenuDto> getMenu();
}
