package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;

public interface PasajerosRepositoryBI extends BaseRepositoryBI {
	
	PasajeroDTO getPasajero(Integer id);
	List<PasajeroDTO> getPasajeros();
	void addCredito(Integer pasajeroId, Double monto);
}

