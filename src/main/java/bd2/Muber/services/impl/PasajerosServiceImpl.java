package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.services.PasajerosServiceBI;

public class PasajerosServiceImpl extends BaseServiceImpl implements PasajerosServiceBI
{
	
	public List<PasajeroDTO> getPasajeros(){
		
		return pasajerosRepository.getPasajeros();
	}

	@Override
	public PasajeroDTO getPasajero(Integer pasajeroId) {
		return pasajerosRepository.getPasajero(pasajeroId);
	}

	@Override
	public String addCredito(Integer pasajeroId, Double monto) {
		PasajeroDTO pasajero = this.getPasajero(pasajeroId);
		if (pasajero.getIdUsuario() != 0){
			if (monto > 0){
				pasajerosRepository.addCredito(pasajeroId, monto);
				return "Credito agregado";
			}else{
				return "Ingreso un monto valido";
			}
		}else{
			return "No se encontro pasajero con el Id ingresado";
		}
	}

}