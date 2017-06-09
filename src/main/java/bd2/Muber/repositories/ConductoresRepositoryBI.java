package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.dto.ConductorDTO;

public interface ConductoresRepositoryBI extends BaseRepositoryBI {

	ConductorDTO getConductor(Integer conductorId);
	List<ConductorDTO> getConductores();
	List<ConductorDTO> obtenerTop10();
	
}

