package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.model.Conductor;

public interface ConductoresRepositoryBI extends BaseRepositoryBI {

	Conductor getConductor(Integer conductorId);
	List<Conductor> getConductores();
	List<Conductor> obtenerTop10();
	
}

