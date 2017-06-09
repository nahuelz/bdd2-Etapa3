package bd2.Muber.repositories;

public class RepositoryLocator {
	
	static PasajerosRepositoryBI pasajerosRepository;
	static ConductoresRepositoryBI conductoresRepository;
	static ViajesRepositoryBI viajesRepository;
	
    public static RepositoryLocator getInstance() {
        return new RepositoryLocator();
    }
    
    public static void setPasajerosRepository(PasajerosRepositoryBI repository){
    	pasajerosRepository = repository;
    }
    
    public static PasajerosRepositoryBI getPasajerosRepository(){
    	return pasajerosRepository;
    }
    
    public static void setConductoresRepository(ConductoresRepositoryBI repository){
    	conductoresRepository = repository;
    }
    
    public static ConductoresRepositoryBI getConductoresRepository(){
    	return conductoresRepository;
    }
    
    public static void setViajesRepository(ViajesRepositoryBI repository){
    	viajesRepository = repository;
    }
    
    public static ViajesRepositoryBI getViajesRepository(){
    	return viajesRepository;
    }
	
}