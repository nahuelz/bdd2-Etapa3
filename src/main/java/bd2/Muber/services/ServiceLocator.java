package bd2.Muber.services;

public class ServiceLocator {
	static PasajerosServiceBI pasajerosService;
	
	public static PasajerosServiceBI getPasajerosService(){
		return pasajerosService;
	}

	public static void setPasajerosService(PasajerosServiceBI pasajerosService) {
		ServiceLocator.pasajerosService = pasajerosService;
	}
	
    public static ServiceLocator getInstance() {
        return new ServiceLocator();
    }	

}

