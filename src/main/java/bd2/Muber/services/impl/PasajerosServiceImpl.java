package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.services.PasajerosServiceBI;

public class PasajerosServiceImpl extends BaseServiceImpl implements PasajerosServiceBI
{
	
	public List<PasajeroDTO> getPasajeros(){
		
		return pasajerosRepository.getPasajeros();
	}
}