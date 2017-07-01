package bd2.Muber.repositories.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bd2.Muber.model.*;
import bd2.Muber.repositories.PasajerosRepositoryBI;

public class HibernatePasajerosRepository extends BaseHibernateRepository implements PasajerosRepositoryBI{	
	
	public HibernatePasajerosRepository(){
		super();
	}
	
	public Pasajero getPasajero(Integer id){
		Session session = this.getSession();
		Query query =session.createQuery("from Pasajero WHERE id_usuario = :id");
		query.setParameter("id", id);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		session.disconnect();
		session.close();
		return pasajero;
	}
	
	public List<Pasajero> getPasajeros(){
		Session session = this.getSession();
		List<Pasajero> pasajeros = session.createQuery("from Pasajero").list();
		session.disconnect();
		session.close();		
		return pasajeros;
	}

	
	public void addCredito(Integer pasajeroId, Double monto){
		Session session = this.getSession();

		// La validacion de que exista el pasajero se hace en el service
		Query query = session.createQuery("from Pasajero WHERE id_usuario = :pasajeroId");
		query.setParameter("pasajeroId", pasajeroId);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		pasajero.cargarCredito(monto);
			
		Query query2 = session.createQuery("UPDATE Usuario SET creditos = :monto WHERE id = :pasajeroId");
		query2.setParameter("monto", pasajero.getCreditos());
		query2.setParameter("pasajeroId", pasajeroId);
		query2.executeUpdate();
		
		session.disconnect();
		session.close();
	}

	
	
}