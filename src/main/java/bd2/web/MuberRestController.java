package bd2.web;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import bd2.Muber.dto.ConductorDTO;
import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.dto.ViajeDTO;
import bd2.Muber.services.ConductoresServiceBI;
import bd2.Muber.services.PasajerosServiceBI;
import bd2.Muber.services.ServiceLocator;
import bd2.Muber.services.ViajesServiceBI;


@Controller
@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {
	
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		/* 
		 * curl http://localhost:8080/MuberRESTful/rest/services/pasajeros
		 */

		Map<String, Object> mapAll = new LinkedHashMap<String, Object>();
		Map<Integer, Object> mapPasajeros = new LinkedHashMap<Integer, Object>();
		Map<String, Object> mapAtributos = new LinkedHashMap<String, Object>();
		PasajerosServiceBI service = ServiceLocator.getPasajerosService();
		List <PasajeroDTO> pasajeros = service.getPasajeros();  
		for (PasajeroDTO p : pasajeros) {
			mapAtributos.put("nombre", p.getNombre());
			mapAtributos.put("password", p.getPassword());
			mapAtributos.put("creditos", p.getCreditos());
			mapAtributos.put("fechaIngreso", p.getFechaIngreso());
			mapPasajeros.put(p.getIdUsuario(), new LinkedHashMap<String, Object>(mapAtributos));
		}
		mapAll.put("result", "OK");
		mapAll.put("pasajeros", mapPasajeros);
		return new Gson().toJson(mapAll);
		
	}
	
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {		
		/*
		 * curl http://localhost:8080/MuberRESTful/rest/services/conductores
		 */
		Map<String, Object> mapAll = new LinkedHashMap<String, Object>();
		Map<Integer, Object> mapConductores = new LinkedHashMap<Integer, Object>();
		Map<String, Object> mapAtributos = new LinkedHashMap<String, Object>();
		ConductoresServiceBI service = ServiceLocator.getConductoresService();
		List <ConductorDTO> conductores = service.getConductores(); 
		for (ConductorDTO c : conductores) {
			mapAtributos.put("nombre", c.getNombre());
			mapAtributos.put("password", c.getPassword());
			mapAtributos.put("fechaVencimientoLic", c.getFechaVencimientoLic());
			mapAtributos.put("fechaIngreso", c.getFechaIngreso());
			mapConductores.put(c.getIdUsuario(), new LinkedHashMap<String, Object>(mapAtributos));
		}
		mapAll.put("result", "OK");
		mapAll.put("conductores", mapConductores);
		return new Gson().toJson(mapAll);
		
	}
	
	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String viajesAbiertos() {
		/*
		 * curl http://localhost:8080/MuberRESTful/rest/services/viajes/abiertos
		 */
		
		Map<String, Object> mapAll = new LinkedHashMap<String, Object>();
		Map<Integer, Object> mapViajes = new LinkedHashMap<Integer, Object>();
		Map<String, Object> mapAtributos = new LinkedHashMap<String, Object>();
		ViajesServiceBI service = ServiceLocator.getViajesService();
		List <ViajeDTO> viajes = service.getViajes(); 
		boolean hayViajes = false;
		for (ViajeDTO v : viajes){
			if (v.isAbierto()){
				hayViajes = true;
				mapAtributos.put("origen", v.getOrigen());
				mapAtributos.put("destino", v.getDestino());
				mapAtributos.put("costoTotal", v.getCostoTotal());
				mapAtributos.put("fecha", v.getFecha());
				mapAtributos.put("cantidadMaximaPasajeros", v.getCantidadMaximaPasajeros());
				mapViajes.put(v.getIdViaje(), new LinkedHashMap<String, Object>(mapAtributos));
			}
		}
		if (hayViajes){
			mapAll.put("result", "OK");
			mapAll.put("viajesAbiertos", mapViajes);
		}else{
			mapAll.put("result", "No se encontraron viajes abiertos");
		}
		return new Gson().toJson(mapAll);
		
	}
	
	@RequestMapping(value = "/viajes/nuevo", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String nuevo(@RequestParam("origen") String origen, @RequestParam("destino") String destino, @RequestParam("conductorId") Integer conductorId, @RequestParam("costoTotal") Integer costoTotal, @RequestParam("cantidadPasajeros") Integer cantidadPasajeros) {
		/*
		 * curl -X POST -d "origen=Mar del Plata&destino=Capital Federal&conductorId=2&costoTotal=1000&cantidadPasajeros=6" "http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo"
		 */
		
		Map<String, Object> aMap = new HashMap<String, Object>();
		if ( (origen != null) & (destino != null) & (conductorId != null) & (costoTotal != null) & (cantidadPasajeros != null) ) {
			if ( (!origen.isEmpty()) & (!destino.isEmpty()) & (!(conductorId == 0)) & (!(costoTotal == 0)) & (!(cantidadPasajeros == 0)) ){
				ViajesServiceBI service = ServiceLocator.getViajesService();
				String resultado = service.altaViaje(origen, destino, conductorId, costoTotal, cantidadPasajeros);
				aMap.put("result", resultado);
			}else{
				aMap.put("result", "Error, parametros incorrectos");
			}
		}else{ 
			aMap.put("result", "Error, parametros incorrectos");
		}
		return new Gson().toJson(aMap);
	}
	
	
	@RequestMapping(value = "/conductores/detalle/{conductorId}", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String detalle(@PathVariable Integer conductorId) {
		/*
		 * curl http://localhost:8080/MuberRESTful/rest/services/conductores/detalle/2
		 */
		Map<String, Object> mapAll = new LinkedHashMap<String, Object>();
		Map<Object, Object> mapConductor = new LinkedHashMap<Object, Object>();
		Map<String, Object> mapViajes = new LinkedHashMap<String, Object>();
		if (conductorId != null){
			ConductoresServiceBI service = ServiceLocator.getConductoresService();
			ConductorDTO conductor = service.getConductor(conductorId); 
			if (conductor.getIdUsuario() != 0){
				mapConductor.put("idUsuario", conductor.getIdUsuario());
				mapConductor.put("nombre", conductor.getNombre());
				mapConductor.put("fechaVencimientoLic", conductor.getFechaVencimientoLic());
				mapConductor.put("Puntaje promedio", conductor.getPuntajePromedio());
				for (ViajeDTO v : conductor.getViajes()){
					mapViajes.put("Id", v.getIdViaje());
					mapViajes.put("origen", v.getOrigen());
					mapViajes.put("Destino", v.getDestino());
					mapViajes.put("Estado", v.getEstado());
					mapConductor.put(v.getIdViaje(), new LinkedHashMap<String, Object>(mapViajes));
				}
				mapAll.put("result", "OK");
				mapAll.put("conductor", mapConductor);
			}else{
				mapAll.put("result", "El ID no corresponde a un conductor");
			}
		}else{
			mapAll.put("result", "No se ingreso un parametro");
		}
		return new Gson().toJson(mapAll);	
	}
	
	@RequestMapping(value = "/viajes/agregarPasajero", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarPasajero(@RequestBody Map<String, Integer> params) {		
		/*
		 * Probado con Postman {"viajeId":1,"pasajeroId":3}
		 */
		Map<String, Object> aMap = new HashMap<String, Object>();
		if ( (params.get("viajeId") != null) & (params.get("pasajeroId") != null) ){
			ViajesServiceBI service = ServiceLocator.getViajesService();
			String resultado = service.addPasajero(params.get("viajeId"), params.get("pasajeroId"));
			aMap.put("Result", resultado);
		}else{
			aMap.put("result", "Error, parametros incorrectos");
		}
		return new Gson().toJson(aMap);
	}
	
	
	@RequestMapping(value = "/viajes/calificar", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String calificar(@RequestParam("viajeId") Integer viajeId, @RequestParam("pasajeroId") Integer pasajeroId, @RequestParam("puntaje") Integer puntaje, @RequestParam("comentario") String comentario) {	
		/*
		 * curl -X POST -d "viajeId=1&pasajeroId=1&puntaje=4&comentario=Conductor agradable" "http://localhost:8080/MuberRESTful/rest/services/viajes/calificar"
		 */
		Map<String, Object> aMap = new HashMap<String, Object>();
		if ( (viajeId != null) & (pasajeroId != null) & (puntaje != null) & (comentario != null)){
			ViajesServiceBI service = ServiceLocator.getViajesService();
			String resultado = service.calificarViaje(viajeId, pasajeroId, puntaje, comentario);
			aMap.put("result", resultado);
		}else
			aMap.put("result", "Error, parametros incorrectos");
		return new Gson().toJson(aMap);
	}
	
	@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String top10() {
		
		/*
		 * curl http://localhost:8080/MuberRESTful/rest/services/conductores/top10
		 */
		
		Map<String, Object> mapAll = new LinkedHashMap<String, Object>();
		Map<Integer, Object> mapConductores = new LinkedHashMap<Integer, Object>();
		Map<String, Object> mapAtributos = new LinkedHashMap<String, Object>();
		ConductoresServiceBI service = ServiceLocator.getConductoresService();
		List <ConductorDTO> conductores =  service.obtenerTop10();
		for (ConductorDTO c : conductores) {
			mapAtributos.put("nombre", c.getNombre());
			mapAtributos.put("password", c.getPassword());
			mapAtributos.put("fechaVencimientoLic", c.getFechaVencimientoLic());
			mapAtributos.put("fechaIngreso", c.getFechaIngreso());
			mapAtributos.put("puntajePromedio", Double.toString(c.getPuntajePromedio()));
			mapConductores.put(c.getIdUsuario(), new LinkedHashMap<String, Object>(mapAtributos));
		}
		mapAll.put("result", "OK");
		mapAll.put("conductores", mapConductores);
		return new Gson().toJson(mapAll);
	}
		
	@RequestMapping(value = "/pasajeros/cargarCredito", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String cargarCredito(@RequestBody Map<String, Object> params) {
		/*
		 * Probado con Postman {"pasajeroId":1,"monto":200}
		 */
		
		Double aux = (Double) params.get("pasajeroId");
		Integer pasajeroId =  aux.intValue();
		Double monto = (Double) params.get("monto");
		Map<String, Object> aMap = new HashMap<String, Object>();
		if  ((pasajeroId != null) && (monto != null)) {
			PasajerosServiceBI service = ServiceLocator.getPasajerosService(); 
			String resultado = service.addCredito(pasajeroId, monto);
			aMap.put("result", resultado);
		}else{
			aMap.put("result", "Error parametros incorrectos");
		}
		return new Gson().toJson(aMap);
	}
	
	@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String finalizar(@RequestBody Map<String, Integer> params) {	
		/*
		 * Probado con Postman {"viajeId":1}
		 */
		Map<String, Object> aMap = new HashMap<String, Object>();
		if  (params.get("viajeId") != null) {
			ViajesServiceBI service = ServiceLocator.getViajesService();
			if (service.finalizarViaje(params.get("viajeId"))){
				aMap.put("Result", "Viaje Fianlizado");
			}else{
				aMap.put("Result", "Error, no existe viaje abierto con el id ingresado");
			}
		}else{
			aMap.put("Result", "Error parametro incorrecto");
		}
		return new Gson().toJson(aMap);
	}
}
