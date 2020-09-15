package dw.elh.dto;

import java.util.List;

public class MenuDto {
	private String id;
	private String nombre;
	private String enlace;
	private List<MenuDto> subMenus;
	private String padreId;
	private String padreNombre;

	public String getPadreNombre() {
		return padreNombre;
	}
	public void setPadreNombre(String padreNombre) {
		this.padreNombre = padreNombre;
	}
	public String getPadreId() {
		return padreId;
	}
	public void setPadreId(String padreId) {
		this.padreId = padreId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
