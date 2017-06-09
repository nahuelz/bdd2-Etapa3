package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.ConductorDTO;
import bd2.Muber.services.ConductoresServiceBI;

public class ConductoresServiceImpl extends BaseServiceImpl implements ConductoresServiceBI
{

	public List<ConductorDTO> getConductores() {
		return conductoresRepository.getConductores();
	}

	@Override
	public ConductorDTO getConductor(Integer conductorId) {
		return conductoresRepository.getConductor(conductorId);
	}

	@Override
	public List<ConductorDTO> obtenerTop10() {
		return conductoresRepository.obtenerTop10();
	}
}