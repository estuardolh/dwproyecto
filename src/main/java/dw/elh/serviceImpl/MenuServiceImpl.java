package dw.elh.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
		List<Menu> listaMenus = getMenusPadre();
	
		for(int i = 0; i < listaMenus.size() ; i++ ) {
			Menu menu = listaMenus.get(i);
			
			MenuDto menuDto = new MenuDto();
			menuDto.setId(String.valueOf(menu.getId()));
			menuDto.setNombre(menu.getNombre());
			menuDto.setEnlace(menu.getArchivoHtml());
			
			List<Menu> listaSubMenu = menuRepository.getMenuByMenuPadreId(menu.getId());
			
			List<MenuDto> subMenus = new ArrayList<>();
			for(int j = 0; j < listaSubMenu.size() ; j++) {
				Menu subMenu = listaSubMenu.get(j);
				
				MenuDto subMenuDto = new MenuDto();
				subMenuDto.setId(String.valueOf(subMenu.getId()));
				subMenuDto.setNombre(subMenu.getNombre());
				subMenuDto.setEnlace(subMenu.getArchivoHtml());
				subMenuDto.setSubMenus(null);
				subMenuDto.setPadreId(menuDto.getId());
				subMenuDto.setPadreNombre(menuDto.getNombre());
				
				subMenus.add(subMenuDto);
			}
			menuDto.setSubMenus(subMenus);
			
			listaMenusDto.add(menuDto);
		}
	
		return listaMenusDto;
	}

	@Override
	public void save(Menu menu) {
		menuRepository.save(menu);
	}

	@Override
	public List<Menu> getMenusPadre() {
		return menuRepository.getMenuByMenuPadreIsNullOrderByPosicion();
	}

	@Override
	public List<MenuDto> getMenusListOrderedByPadre() {
		List<Menu> menusOrdenados = menuRepository.getMenusListOrderedBy();
	
		List<MenuDto> listaMenusDto = new ArrayList<>();
	
		for(int i = 0; i < menusOrdenados.size() ; i++ ) {
			Menu menu = menusOrdenados.get(i);
			
			MenuDto menuDto = new MenuDto();
			menuDto.setId(String.valueOf(menu.getId()));
			menuDto.setNombre(menu.getNombre());
			menuDto.setEnlace(menu.getArchivoHtml());
			menuDto.setPadreNombre(ObjectUtils.isEmpty(menu.getMenuPadre())?"":menu.getMenuPadre().getNombre());
			
			listaMenusDto.add(menuDto);
		}
		return listaMenusDto; 
	}
}
