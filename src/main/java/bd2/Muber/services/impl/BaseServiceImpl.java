package bd2.Muber.services.impl;

import bd2.Muber.dto.DTOFactory;
import bd2.Muber.repositories.PasajerosRepositoryBI;

public class BaseServiceImpl
{
	public PasajerosRepositoryBI pasajerosRepository;
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
	
	
}