package dw.elh.dto;

import java.util.List;

public class MenuDto {
	private String nombre;
	private String enlace;
	private List<MenuDto> subMenus;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public List<MenuDto> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuDto> subMenus) {
		this.subMenus = subMenus;
	}
}
