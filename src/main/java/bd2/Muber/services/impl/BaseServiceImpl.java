package bd2.Muber.services.impl;

import bd2.Muber.dto.DTOFactory;
import bd2.Muber.repositories.*;

public class BaseServiceImpl
{
	public PasajerosRepositoryBI pasajerosRepository;
	public ConductoresRepositoryBI conductoresRepository;
	public ViajesRepositoryBI viajesRepository;

	public DTOFactory dtoFactory;
	
	public DTOFactory getDtoFactory() {
		return dtoFactory;
	}

	public void setDtoFactory(DTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}

	public void setPasajerosRepository(PasajerosRepositoryBI repository){
		pasajerosRepository = repository;
	}
	
	public PasajerosRepositoryBI getPasajerosRepository(){
		return pasajerosRepository;
	}
	
	public void setConductoresRepository(ConductoresRepositoryBI repository){
		conductoresRepository = repository;
	}
	
	public ConductoresRepositoryBI getConductoresRepository(){
		return conductoresRepository;
	}
	
	public void setViajesRepository(ViajesRepositoryBI repository) {
		viajesRepository = repository;
	}
	
	public ViajesRepositoryBI getViajesRepository() {
		return viajesRepository;
	}


	
	
}