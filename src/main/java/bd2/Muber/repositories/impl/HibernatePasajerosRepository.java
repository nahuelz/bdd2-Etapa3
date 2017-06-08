package bd2.Muber.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dto.PasajeroDTO;

import bd2.Muber.model.*;
import bd2.Muber.repositories.PasajerosRepositoryBI;

public class HibernatePasajerosRepository extends BaseHibernateRepository implements PasajerosRepositoryBI{	
	
	public HibernatePasajerosRepository(){
		super();
	}
	
	public PasajeroDTO getPasajero(Integer id){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Query query =session.createQuery("from Pasajero WHERE id_usuario = :id");
		query.setParameter("id", id);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		PasajeroDTO pasajeroDTO = new PasajeroDTO(pasajero);
		tx.rollback();
		session.disconnect();
		session.close();
		return pasajeroDTO;
	}
	
	public List<PasajeroDTO> getPasajeros(){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		List<Pasajero> pasajeros = session.createQuery("from Pasajero").list();
		List<PasajeroDTO> pasajerosDTO = new ArrayList<PasajeroDTO>();
		for (Pasajero p : pasajeros) {
			PasajeroDTO pas = new PasajeroDTO(p);
			pasajerosDTO.add(pas);
		}
		tx.rollback();
		session.disconnect();
		session.close();		
		return pasajerosDTO;
	}
	
	public String addPasajero(Integer viajeId, Integer pasajeroId){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Viaje viaje = (Viaje) session.get(Viaje.class, viajeId);
		Pasajero pasajero = (Pasajero) session.get(Pasajero.class, pasajeroId);
		String resultado;
		if (viaje != null){
			if (pasajero != null){
				if (viaje.addPasajero(pasajero)){
					tx.commit();
					resultado = "pasajero agregado";
				}else{
					resultado = "No se pudo agregar el pasajero";
				}
			}else{
				resultado = "No se encontro pasajero con el id ingresado";
			}
		}else{
			resultado = "No se encontro viaje con el id ingresado";			
		}
		session.disconnect();
		session.close();
		return resultado;
	}
	
	public String addCredito(Integer pasajeroId, Double monto){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Pasajero pasajero = (Pasajero) session.get(Pasajero.class, pasajeroId);
		String resultado;
		if (pasajero != null){
			resultado = "Credito agregado";
			pasajero.cargarCredito(monto);
			tx.commit();
		}else{
			resultado = "El ID ingresado no corresponde a un pasajero";
		}
		session.disconnect();
		session.close();
		return resultado;
	}

	
	
}