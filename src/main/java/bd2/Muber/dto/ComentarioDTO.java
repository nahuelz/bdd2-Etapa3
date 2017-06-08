package bd2.Muber.dto;

import bd2.Muber.model.Comentario;
import bd2.Muber.model.Pasajero;

public class ComentarioDTO {
	
	private int idComentario;
	private String comentario;
	private int calificacion;
	private Pasajero pasajero;
	
	public ComentarioDTO (){
		
	}
	
	public ComentarioDTO(int calificacion, String comentario, Pasajero pasajero) {
		this.comentario = comentario;
		this.calificacion = calificacion;
		this.pasajero = pasajero;
	}
	
	public ComentarioDTO(Comentario c) {
		this.setCalificacion(c.getCalificacion());
		this.setComentario(c.getComentario());
		this.setIdComentario(c.getIdComentario());
		this.setPasajero(c.getPasajero());
	}
	
	public int getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

}
