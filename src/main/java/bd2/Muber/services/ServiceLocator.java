package bd2.Muber.services;

public class ServiceLocator {
	static PasajerosServiceBI pasajerosService;
	static ConductoresServiceBI conductoresService;
	static ViajesServiceBI viajesService;
	
    public static ServiceLocator getInstance() {
        return new ServiceLocator();
    }
    
	public static void setPasajerosService(PasajerosServiceBI pasajerosService) {
		ServiceLocator.pasajerosService = pasajerosService;
	}
    
	public static PasajerosServiceBI getPasajerosService(){
		return pasajerosService;
	}
    
	public static void setConductoresService(ConductoresServiceBI conductoresService) {
		ServiceLocator.conductoresService = conductoresService;
	}
	
	public static ConductoresServiceBI getConductoresService() {
		return conductoresService;
	}
	
	public static void setViajesService(ViajesServiceBI viajesService) {
		ServiceLocator.viajesService = viajesService;
	}
	
	public static ViajesServiceBI getViajesService() {
		return viajesService;
	}	

}

