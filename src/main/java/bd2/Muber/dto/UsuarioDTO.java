package bd2.Muber.dto;

import java.util.Date;

public abstract class UsuarioDTO {
	private int idUsuario;
	private String nombre;
	private String password;
	private Date fechaIngreso;
	
	public UsuarioDTO (String nombre, String password, Date fecha){
		this();
		this.setNombre(nombre);
		this.setPassword(password);
		this.setFechaIngreso(fecha);
	}
	
	public UsuarioDTO() {

	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public abstract boolean isPasajero();

	public abstract boolean isConductor();

}
