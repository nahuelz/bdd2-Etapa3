package bd2.Muber.dto;

import java.util.ArrayList;
import java.util.List;

import bd2.Muber.model.Conductor;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;

public class DTOFactory {

	public List<PasajeroDTO> getPasajeros(List<Pasajero> pasajeros) {
		// TODO Auto-generated method stub
		List <PasajeroDTO> pasajerosDTO = new ArrayList<PasajeroDTO>();
			for(Pasajero p: pasajeros){
				PasajeroDTO pasajeroDTO = new PasajeroDTO(p);
				pasajerosDTO.add(pasajeroDTO);
			}
		return pasajerosDTO;
	}

	public PasajeroDTO getPasajero(Pasajero pasajero) {
		// TODO Auto-generated method stub
		PasajeroDTO pasajeroDTO = new PasajeroDTO();
		if (pasajero != null){
			pasajeroDTO = new PasajeroDTO(pasajero);
		}
		return pasajeroDTO;
	}

	public List<ConductorDTO> getConductores(List<Conductor> conductores) {
		// TODO Auto-generated method stub
		List <ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();
		for(Conductor c: conductores){
			ConductorDTO conductorDTO = new ConductorDTO(c);
			conductoresDTO.add(conductorDTO);
		}
		return conductoresDTO;
	}

	public ConductorDTO getConductor(Conductor conductor) {
		// TODO Auto-generated method stub
		ConductorDTO conductorDTO = new ConductorDTO();
		if (conductor != null){
			conductorDTO = new ConductorDTO(conductor);
		}
		return conductorDTO;
	}

	public List<ConductorDTO> obtenerTop10(List<Conductor> conductores) {
		List<ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();
		for (Conductor c : conductores) {
			ConductorDTO con = new ConductorDTO(c);
			con.setPuntajePromedio(c.puntajePromedio());
			conductoresDTO.add(con);
		}
		return conductoresDTO;
	}

	public List<ViajeDTO> getViajes(List<Viaje> viajes) {
		// TODO Auto-generated method stub
		List <ViajeDTO> viajesDTO = new ArrayList<ViajeDTO>();
		for(Viaje v: viajes){
			ViajeDTO viajeDTO = new ViajeDTO(v);
			viajesDTO.add(viajeDTO);
		}
		return viajesDTO;
	}

	public ViajeDTO getViaje(Viaje viaje) {
		// TODO Auto-generated method stub
		ViajeDTO viajeDTO = new ViajeDTO();
		if (viaje != null){
			viajeDTO = new ViajeDTO(viaje);
		}
		return viajeDTO;
	}	

}
