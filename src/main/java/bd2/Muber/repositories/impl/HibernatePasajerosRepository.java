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
		PasajeroDTO pasajeroDTO = new PasajeroDTO();
		if (pasajero != null){
			pasajeroDTO = new PasajeroDTO(pasajero);
		}
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

	
	public void addCredito(Integer pasajeroId, Double monto){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		Query query =session.createQuery("from Pasajero WHERE id_usuario = :pasajeroId");
		query.setParameter("pasajeroId", pasajeroId);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		
		pasajero.cargarCredito(monto);
		tx.commit();
		session.disconnect();
		session.close();
	}

	
	
}