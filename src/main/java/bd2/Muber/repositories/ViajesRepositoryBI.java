package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.model.Viaje;

public interface ViajesRepositoryBI extends BaseRepositoryBI {

	List<Viaje> getViajes();
	Viaje getViaje(Integer viajeId);
	void altaViaje (String origen, String destino, Integer conductorId, Integer costoTotal, Integer cantidadPasajeros);
	String addPasajero(Integer viajeId, Integer pasajeroId);
	void calificarViaje(Integer viajeId, Integer pasajeroId, Integer puntaje, String comentario);
	void finalizarViaje(Integer viajeId);
	List<Viaje> getViajesConductor(Integer conductorId);
	boolean fuePasajero(Integer pasajeroId, Integer viajeId);
	boolean calificoViaje(Integer pasajeroId, Integer viajeId);
	
}

