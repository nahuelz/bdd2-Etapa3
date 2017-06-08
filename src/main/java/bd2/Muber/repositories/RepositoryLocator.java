package bd2.Muber.repositories;

public class RepositoryLocator {
	
	static PasajerosRepositoryBI pasajerosRepository;
	
    public static RepositoryLocator getInstance() {
        return new RepositoryLocator();
    }
    
    public static void setPasajerosRepository(PasajerosRepositoryBI repository){
    	pasajerosRepository = repository;
    }
    
    public static PasajerosRepositoryBI getPasajerosRepository(){
    	return pasajerosRepository;
    }
	
}




