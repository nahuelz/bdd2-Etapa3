package bd2.Muber.services;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;

public interface PasajerosServiceBI {
	
	List<PasajeroDTO> getPasajeros();
	PasajeroDTO getPasajero(Integer pasajeroId);
	String addCredito(Integer pasajeroId, Double monto);

}

