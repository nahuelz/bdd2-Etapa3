package bd2.Muber.repositories.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.model.*;
import bd2.Muber.repositories.ViajesRepositoryBI;

public class HibernateViajesRepository extends BaseHibernateRepository implements ViajesRepositoryBI{

	public HibernateViajesRepository(){
		super();
	}
	
	@Override
	public List<Viaje> getViajes() {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		List<Viaje> viajes = session.createQuery("from Viaje").list();
		tx.rollback();
		session.disconnect();
		session.close();		
		return viajes;

	}

	@Override
	public void altaViaje(String origen, String destino, Integer conductorId, Integer costoTotal,
			Integer cantidadPasajeros) {
		
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Muber muber = (Muber) session.createQuery("from Muber").uniqueResult();
		Query query = session.createQuery("from Conductor WHERE id_usuario = :conductorId");
		query.setParameter("conductorId", conductorId);
		Conductor conductor = (Conductor) query.uniqueResult();;
		Viaje viaje = new Viaje (origen, destino, costoTotal, cantidadPasajeros, new Date(), conductor);
		muber.addViaje(viaje);		
		tx.commit();
		session.disconnect();
		session.close();
		
	}

	@Override
	public Viaje getViaje(Integer viajeId) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Query query =session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje viaje = (Viaje) query.uniqueResult();
		tx.rollback();
		session.disconnect();
		session.close();
		return viaje;
	}

	@Override
	public String addPasajero(Integer viajeId, Integer pasajeroId) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje viaje = (Viaje) query.uniqueResult();

		Query queryPasajero = session.createQuery("from Pasajero WHERE id = :pasajeroId");
		queryPasajero.setParameter("pasajeroId", pasajeroId);
		Pasajero pasajero = (Pasajero) queryPasajero.uniqueResult();
		
		String resultado;

		if (viaje.addPasajero(pasajero)){
			tx.commit();
			resultado = "pasajero agregado";
		}else{
			tx.rollback();
			resultado = "No se pudo agregar el pasajero";
		}

		session.disconnect();
		session.close();
		return resultado;
		
	}

	@Override
	public void calificarViaje(Integer viajeId, Integer pasajeroId, Integer puntaje, String comentario) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje viaje = (Viaje) query.uniqueResult();

		Query queryPasajero = session.createQuery("from Pasajero WHERE id = :pasajeroId");
		queryPasajero.setParameter("pasajeroId", pasajeroId);
		Pasajero pasajero = (Pasajero) queryPasajero.uniqueResult();
		
		// Las validaciones previas al agregar el comentario se realizaron en el service
		Comentario c = new Comentario (puntaje, comentario, pasajero);
		viaje.addComentario(c);
		tx.commit();
		session.disconnect();
		session.close();
	}

	@Override
	public void finalizarViaje(Integer viajeId) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje viaje = (Viaje) query.uniqueResult();
		
		// Las validaciones previas al finalizar el viaje se realizaron en el service
		viaje.finalizar();
		tx.commit();
		session.disconnect();
		session.close();
		
	}
	
	
}