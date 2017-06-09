package bd2.Muber.services;

import java.util.List;
import bd2.Muber.dto.ConductorDTO;

public interface ConductoresServiceBI {
	
	List<ConductorDTO> getConductores();

	ConductorDTO getConductor(Integer conductorId);

	List<ConductorDTO> obtenerTop10();

}