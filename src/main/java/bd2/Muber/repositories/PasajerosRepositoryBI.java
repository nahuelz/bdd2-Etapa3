package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.model.Pasajero;

public interface PasajerosRepositoryBI extends BaseRepositoryBI {
	
	Pasajero getPasajero(Integer id);
	List<Pasajero> getPasajeros();
	void addCredito(Integer pasajeroId, Double monto);
}

