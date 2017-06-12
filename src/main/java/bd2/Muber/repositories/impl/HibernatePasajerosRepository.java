package bd2.Muber.repositories.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.model.*;
import bd2.Muber.repositories.PasajerosRepositoryBI;

public class HibernatePasajerosRepository extends BaseHibernateRepository implements PasajerosRepositoryBI{	
	
	public HibernatePasajerosRepository(){
		super();
	}
	
	public Pasajero getPasajero(Integer id){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Query query =session.createQuery("from Pasajero WHERE id_usuario = :id");
		query.setParameter("id", id);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		tx.rollback();
		session.disconnect();
		session.close();
		return pasajero;
	}
	
	public List<Pasajero> getPasajeros(){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		List<Pasajero> pasajeros = session.createQuery("from Pasajero").list();
		tx.rollback();
		session.disconnect();
		session.close();		
		return pasajeros;
	}

	
	public void addCredito(Integer pasajeroId, Double monto){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		// La validacion de que exista el pasajero se hace en el service
		Query query =session.createQuery("from Pasajero WHERE id_usuario = :pasajeroId");
		query.setParameter("pasajeroId", pasajeroId);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		
		pasajero.cargarCredito(monto);
		tx.commit();
		session.disconnect();
		session.close();
	}

	
	
}