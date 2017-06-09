package bd2.Muber.services;

import java.util.List;
import bd2.Muber.dto.ViajeDTO;

public interface ViajesServiceBI {
	
	List<ViajeDTO> getViajes();

	String altaViaje(String origen, String destino, Integer conductorId, Integer costoTotal, Integer cantidadPasajeros);

	String addPasajero(Integer integer, Integer integer2);

	String calificarViaje(Integer viajeId, Integer pasajeroId, Integer puntaje, String comentario);

	boolean finalizarViaje(Integer viajeId);

}