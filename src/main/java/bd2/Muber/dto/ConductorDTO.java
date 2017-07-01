package bd2.Muber.dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import bd2.Muber.model.Conductor;

public class ConductorDTO extends UsuarioDTO  {
	
	private int idConductor;
	private Date fechaVencimientoLic;
	private double puntajePromedio;
	private Set<ViajeDTO> viajes;

	public ConductorDTO(){
		super();
	}
	
	public ConductorDTO(String nombre, String password, Date fechaIngreso, Date fechaVencimiento){
		super(nombre, password, fechaIngreso);
		this.setFechaVencimientoLic(fechaVencimiento);
	}

	public ConductorDTO(Conductor c) {
		this.setFechaIngreso(c.getFechaIngreso());
		this.setFechaVencimientoLic(c.getFechaVencimientoLic());
		this.setIdUsuario(c.getIdUsuario());
		this.setNombre(c.getNombre());
		this.setPassword(c.getPassword());
		this.setIdConductor(c.getIdConductor());
		this.setPuntajePromedio(c.getPuntajePromedio());
	}

	public Date getFechaVencimientoLic() {
		return fechaVencimientoLic;
	}

	public void setFechaVencimientoLic(Date fechaVencimientoLic) {
		this.fechaVencimientoLic = fechaVencimientoLic;
	}
	
	public int getIdConductor() {
		return idConductor;
	}

	public void setIdConductor(int idConductor) {
		this.idConductor = idConductor;
	}
	
	public boolean isPasajero() {
		return false;
	}

	public boolean isConductor() {
		return true;
	}
	
	public double getPuntajePromedio() {
		return puntajePromedio;
	}

	public void setPuntajePromedio(double puntajePromedio) {
		this.puntajePromedio = puntajePromedio;
	}

	public Set<ViajeDTO> getViajes() {
		return viajes;
	}

	public void setViajes(Set<ViajeDTO> viajes) {
		this.viajes = viajes;
	}

}

