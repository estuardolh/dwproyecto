package dw.elh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dw.elh.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Integer>{
	List<Menu> getMenuByMenuPadreIsNullOrderByPosicion();
	@Query(value = "SELECT * FROM menu m WHERE m.menu_padre_id = ?1", nativeQuery = true)
	List<Menu> getMenuByMenuPadreId(Long padreId);
	@Query(value = "SELECT m.* FROM menu m ORDER BY menu_padre_id, posicion ASC", nativeQuery = true)
	List<Menu> getMenusListOrderedBy();
	
}
