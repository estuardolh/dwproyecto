package dw.elh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dw.elh.dto.UsuarioDto;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String usuario;
	private String clave;
	private String nombre;
	
	public Usuario(UsuarioDto usuarioDto) {
		this.usuario = usuarioDto.getNombreUsuario();
		this.nombre = usuarioDto.getNombre();
		this.clave = usuarioDto.getClave();
	}
	
	public Usuario() {
		super();
	}
	
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
}
