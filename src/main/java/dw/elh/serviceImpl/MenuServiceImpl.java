package dw.elh.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dw.elh.dto.MenuDto;
import dw.elh.model.Menu;
import dw.elh.repository.MenuRepository;
import dw.elh.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepository menuRepository;

	@Override
	public List<MenuDto> getMenu() {
		List<MenuDto> listaMenusDto = new ArrayList<>();
		List<Menu> listaMenus = menuRepository.getMenuByMenuPadreIsNullOrderByPosicion();
	
		for(int i = 0; i < listaMenus.size() ; i++ ) {
			Menu menu = listaMenus.get(i);
			
			MenuDto menuDto = new MenuDto();
			menuDto.setNombre(menu.getNombre());
			menuDto.setEnlace(menu.getArchivoHtml());
			
			List<Menu> listaSubMenu = menuRepository.getMenuByMenuPadreId(menu.getId());
			
			List<MenuDto> subMenus = new ArrayList<>();
			for(int j = 0; j < listaSubMenu.size() ; j++) {
				Menu subMenu = listaSubMenu.get(j);
				
				MenuDto subMenuDto = new MenuDto();
				subMenuDto.setNombre(subMenu.getNombre());
				subMenuDto.setEnlace(subMenu.getArchivoHtml());
				subMenuDto.setSubMenus(null);
				
				subMenus.add(subMenuDto);
			}
			menuDto.setSubMenus(subMenus);
			
			listaMenusDto.add(menuDto);
		}
	
		return listaMenusDto;
	}
	
}
