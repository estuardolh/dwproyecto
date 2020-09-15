package dw.elh.dto;

public class UsuarioDto {
	private String id;
	private String usuario;
	private String clave;
	private String nombre;
	private String perfilId;
	private String intentos;
	
	public UsuarioDto(String usuario, String clave, String nombre, String perfilId, String intentos) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.perfilId = perfilId;
		this.intentos = intentos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getPerfilId() {
		return perfilId;
	}
	public void setPerfilId(String perfilId) {
		this.perfilId = perfilId;
	}
	public String getIntentos() {
		return intentos;
	}
	public void setIntentos(String intentos) {
		this.intentos = intentos;
	}
}
