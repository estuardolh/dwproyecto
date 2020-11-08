package dw.elh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String usuario;
	private String clave;
	private String nombre;
	@ManyToOne
	@JoinColumn(name="perfil_id")
	private Perfil perfil;
	private Long intentos;
	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu;
	private String colorBarra;
	private String colorFondo;
	private String colorLetra;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Long getIntentos() {
		return intentos;
	}
	public void setIntentos(Long intentos) {
		this.intentos = intentos;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getColorBarra() {
		return colorBarra;
	}
	public void setColorBarra(String colorBarra) {
		this.colorBarra = colorBarra;
	}
	public String getColorFondo() {
		return colorFondo;
	}
	public void setColorFondo(String colorFondo) {
		this.colorFondo = colorFondo;
	}
	public String getColorLetra() {
		return colorLetra;
	}
	public void setColorLetra(String colorLetra) {
		this.colorLetra = colorLetra;
	}
}
