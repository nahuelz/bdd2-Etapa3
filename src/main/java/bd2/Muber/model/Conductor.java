package bd2.Muber.model;
import java.util.Date;
import java.util.Set;

public class Conductor extends Usuario {
	
	private int idConductor;
	private Date fechaVencimientoLic;
	private float puntajePromedio;
	
	public Conductor(){
		super();
	}
	
	public Conductor(String nombre, String password, Date fechaIngreso, Date fechaVencimiento){
		super(nombre, password, fechaIngreso);
		this.setFechaVencimientoLic(fechaVencimiento);
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
	
	public void setPuntajePromedio (float promedio){
		this.puntajePromedio = promedio;
	}
	
	public Float getPuntajePromedio (){
		return puntajePromedio;
	}
	
	public Float puntajePromedio() {
		if (this.getViajes().isEmpty()){
			this.setPuntajePromedio(0);
			return (float) 0;
		}else{
			float promedio = 0;
			int cantViajes = 0;
			Set<Viaje> viajes = this.getViajes();
			for (Viaje viaje : viajes ) {
				if (viaje.getComentarios().size() != 0){
					cantViajes ++;
					promedio = promedio + viaje.puntajePromedio();
				}
			}
			this.setPuntajePromedio(promedio/cantViajes);
			return promedio/cantViajes;
		}
	}

}
