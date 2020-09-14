package dw.elh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long posicion;
	private String nombre;
	@ManyToOne
	@JoinColumn(name="menu_padre_id")
	private Menu menuPadre;
	private String archivoHtml;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPosicion() {
		return posicion;
	}
	public void setPosicion(Long posicion) {
		this.posicion = posicion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Menu getMenuPadre() {
		return menuPadre;
	}
	public void setMenuPadre(Menu menuPadre) {
		this.menuPadre = menuPadre;
	}
	public String getArchivoHtml() {
		return archivoHtml;
	}
	public void setArchivoHtml(String archivoHtml) {
		this.archivoHtml = archivoHtml;
	}
}
