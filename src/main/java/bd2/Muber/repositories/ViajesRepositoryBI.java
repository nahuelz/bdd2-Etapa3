package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.dto.ViajeDTO;

public interface ViajesRepositoryBI extends BaseRepositoryBI {

	List<ViajeDTO> getViajes();
	ViajeDTO getViaje(Integer viajeId);
	void altaViaje (String origen, String destino, Integer conductorId, Integer costoTotal, Integer cantidadPasajeros);
	String addPasajero(Integer viajeId, Integer pasajeroId);
	void calificarViaje(Integer viajeId, Integer pasajeroId, Integer puntaje, String comentario);
	void finalizarViaje(Integer viajeId);
	
}

