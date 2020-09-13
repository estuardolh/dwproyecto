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

	Long getId() {
		return id;
	}
	void setId(Long id) {
		this.id = id;
	}
	public Long getPosicion() {
		return posicion;
	}
	public void setPosicion(Long posicion) {
		this.posicion = posicion;
	}
	String getNombre() {
		return nombre;
	}
	void setNombre(String nombre) {
		this.nombre = nombre;
	}
	Menu getMenuPadre() {
		return menuPadre;
	}
	void setMenuPadre(Menu menuPadre) {
		this.menuPadre = menuPadre;
	}
	String getArchivoHtml() {
		return archivoHtml;
	}
	void setArchivoHtml(String archivoHtml) {
		this.archivoHtml = archivoHtml;
	}
}
