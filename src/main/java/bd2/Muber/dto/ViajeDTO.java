package bd2.Muber.dto;

import java.util.Date;

import bd2.Muber.model.Conductor;
import bd2.Muber.model.Viaje;


public class ViajeDTO {
	
	private int idViaje;
	private String origen;
	private String destino;
	private int costoTotal;
	private Date fecha;
	private int cantidadMaximaPasajeros;
	private ConductorDTO conductor;
	private char estado;

	public ViajeDTO() {
		this.estado = 'A';
	}
	
	public ViajeDTO(String origen, String destino, int costoTotal, int pasajeros, Date fecha, Conductor conductor){
		this();
		if (conductor.getFechaVencimientoLic().after(fecha)) {
			this.setOrigen(origen);
			this.setDestino(destino);
			this.setCantidadMaximaPasajeros(pasajeros);
			this.setFecha(fecha);
			this.setCostoTotal(costoTotal);
			this.setConductor(new ConductorDTO(conductor));
		}
	}
	
	public ViajeDTO(Viaje v) {
		this.setIdViaje(v.getIdViaje());
		this.setOrigen(v.getOrigen());
		this.setDestino(v.getDestino());
		this.setCostoTotal(v.getCostoTotal());
		this.setEstado(v.getEstado());
		this.setFecha(v.getFecha());
		this.setCantidadMaximaPasajeros(v.getCantidadMaximaPasajeros());	
	}

	public int getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public int getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}
	public void setCantidadMaximaPasajeros(int cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}
	
	public ConductorDTO getConductor() {
		return conductor;
	}

	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}
	
	public char getEstado() {
		return estado;
	}
	
	private void setEstado(char estado) {
		this.estado = estado;
	}
	
	public void cerrar() {
		this.setEstado('C');
	}
	
	public boolean isAbierto() {
		return estado == 'A';
	}
	
	public boolean isCerrado() {
		return estado == 'C';
	}
	
	public boolean isFinalizado() {
		return estado == 'F';
	}
	

}
