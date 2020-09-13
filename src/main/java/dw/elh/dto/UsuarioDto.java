package dw.elh.dto;

public class UsuarioDto {
	private String nombreUsuario;
	private String nombre;
	private String apellido;
	private String clave;
	
	public UsuarioDto(String nombreUsuario, String nombre, String apellido) {
		this.nombreUsuario = nombreUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	@Override
	public String toString() {
		return "nombreUsuario: "+nombreUsuario+", nombre: "+nombre+", apellido: "+apellido+", clave: "+clave;
		
	}
}
