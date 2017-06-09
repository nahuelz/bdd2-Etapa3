package bd2.Muber.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import bd2.Muber.dto.ComentarioDTO;
import bd2.Muber.dto.ConductorDTO;
import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.dto.ViajeDTO;
import bd2.Muber.services.ConductoresServiceBI;
import bd2.Muber.services.PasajerosServiceBI;
import bd2.Muber.services.ServiceLocator;
import bd2.Muber.services.ViajesServiceBI;

public class ViajesServiceImpl extends BaseServiceImpl implements ViajesServiceBI
{
	
	public List<ViajeDTO> getViajes(){
		
		return viajesRepository.getViajes();
	}
	
	public ViajeDTO getViaje(Integer viajeId){
		return viajesRepository.getViaje(viajeId);
	}

	@Override
	public String altaViaje(String origen, String destino, Integer conductorId, Integer costoTotal, Integer cantidadPasajeros)
	{
		ConductoresServiceBI service = ServiceLocator.getConductoresService();
		ConductorDTO conductor = service.getConductor(conductorId); 
		if (conductor != null){
			if (conductor.getFechaVencimientoLic().after(new Date())) {
				viajesRepository.altaViaje(origen, destino, conductorId, costoTotal, cantidadPasajeros);
				return "Viaje creado";
			}else{
				return "El conductor posee la lisencia vencida";
			}
		}else{
			return "No se encontro conductor con el Id ingresado";
		}
	}

	@Override
	public String addPasajero(Integer viajeId, Integer pasajeroId) {
		
		ViajeDTO viaje = this.getViaje(viajeId);
		
		PasajerosServiceBI service = ServiceLocator.getPasajerosService();
		PasajeroDTO pasajero = service.getPasajero(pasajeroId);

		// El id en 0 significa que se seteo el id por default en 0 en el dto al no encontrar un viaje/pasajero
		if ( viaje.getIdViaje() != 0){
			if (viaje.isAbierto()){
				if (pasajero.getIdUsuario() != 0 ){
					return viajesRepository.addPasajero(viajeId, pasajeroId);
				}else{
					return "No se encontro pasajero con el Id ingresado";
				}
			}else{
				return "El viaje se encuentra finalizado";
			}
		}else{
			return "No se encontro el viaje";
		}		
	}

	@Override
	public String calificarViaje(Integer viajeId, Integer pasajeroId, Integer puntaje, String comentario) {
		
		ViajeDTO viaje = this.getViaje(viajeId);
		PasajerosServiceBI service = ServiceLocator.getPasajerosService();
		PasajeroDTO pasajero = service.getPasajero(pasajeroId);
		
		// El id en 0 significa que se inicializo el id por default en 0 en el dto al no encontrar un viaje/pasajero
		if ( viaje.getIdViaje() != 0){
			if (!viaje.isAbierto()){
				if (pasajero.getIdUsuario() != 0 ){
					if (this.isPasajero(viaje, pasajero)){
						if (!this.calificoViaje(viaje, pasajero)){
							viajesRepository.calificarViaje(viajeId, pasajeroId, puntaje, comentario);
							return "Viaje calificado";
						}else{
							return "El pasajero ya realizo su calificacion en este viaje";
						}
					}else{
						return "El pasajero no fue pasajero (valga la redundacia) del viaje ingresado";
					}
				}else{
					return "El Id ingresado no corresponde a un pasajero";
				}
			}else{
				return "El viaje aún se encuentra abierto";
			}
		}else{
			return "No se encontro un viaje con el Id ingresado";
		}
	}

	private boolean isPasajero(ViajeDTO viaje, PasajeroDTO pasajero) {
		Set<PasajeroDTO> pasajeros = viaje.getPasajeros();
		for (PasajeroDTO p : pasajeros) {
			 if ( p.getIdUsuario() == (pasajero.getIdUsuario())){
				 return true;
			 }
		 }
		 return false;
	}
	
	private boolean calificoViaje(ViajeDTO viaje, PasajeroDTO pasajero) {
		Set<ComentarioDTO> comentarios = viaje.getComentarios();
		for (ComentarioDTO c : comentarios) {
			if (c.getPasajero().getIdUsuario() == pasajero.getIdUsuario()){ 
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean finalizarViaje(Integer viajeId) {
		ViajeDTO viaje = this.getViaje(viajeId);
		if (viaje.getIdViaje() != 0){
			if (viaje.isAbierto()){
				viajesRepository.finalizarViaje(viajeId);
				return true;
			}
		}
		return false;
	}
}