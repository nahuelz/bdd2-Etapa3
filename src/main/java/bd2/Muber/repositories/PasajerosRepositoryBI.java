package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;

public interface PasajerosRepositoryBI extends BaseRepositoryBI {
	
	public PasajeroDTO getPasajero(Integer id);
	public List<PasajeroDTO> getPasajeros();
	public String addPasajero(Integer viajeId, Integer pasajeroId);
	public String addCredito(Integer pasajeroId, Double monto);
}

