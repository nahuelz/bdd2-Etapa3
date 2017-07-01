package bd2.Muber.repositories.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bd2.Muber.model.*;
import bd2.Muber.repositories.ViajesRepositoryBI;

public class HibernateViajesRepository extends BaseHibernateRepository implements ViajesRepositoryBI{

	public HibernateViajesRepository(){
		super();
	}
	
	@Override
	public List<Viaje> getViajes() {
		Session session = this.getSession();
		List<Viaje> viajes = session.createQuery("from Viaje").list();
		session.disconnect();
		session.close();		
		return viajes;
	}

	@Override
	public void altaViaje(String origen, String destino, Integer conductorId, Integer costoTotal,
			Integer cantidadPasajeros) {
		Session session = this.getSession();
		Muber muber = (Muber) session.createQuery("from Muber").uniqueResult();
		Query query = session.createQuery("from Conductor WHERE id_usuario = :conductorId");
		query.setParameter("conductorId", conductorId);
		Conductor conductor = (Conductor) query.uniqueResult();;
		Viaje viaje = new Viaje (origen, destino, costoTotal, cantidadPasajeros, new Date(), conductor);
		muber.addViaje(viaje);
		session.save("Viaje", viaje);
		session.flush();
		session.disconnect();
		session.close();
		
	}

	@Override
	public Viaje getViaje(Integer viajeId) {
		Session session = this.getSession();
		Query query =session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje viaje = (Viaje) query.uniqueResult();
		session.disconnect();
		session.close();
		return viaje;
	}

	@Override
	public String addPasajero(Integer viajeId, Integer pasajeroId) {
		Session session = this.getSession();
		
		Query query = session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje viaje = (Viaje) query.uniqueResult();

		Query queryPasajero = session.createQuery("from Pasajero WHERE id = :pasajeroId");
		queryPasajero.setParameter("pasajeroId", pasajeroId);
		Pasajero pasajero = (Pasajero) queryPasajero.uniqueResult();
		
		String resultado;

		if (viaje.addPasajero(pasajero)){
			resultado = "pasajero agregado";
			
		}else{
			resultado = "No se pudo agregar el pasajero";
		}
		session.save("Viaje", viaje);
		session.flush();
		session.disconnect();
		session.close();
		return resultado;
		
	}

	@Override
	public void calificarViaje(Integer viajeId, Integer pasajeroId, Integer puntaje, String comentario) {
		Session session = this.getSession();

		Query query = session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje viaje = (Viaje) query.uniqueResult();

		Query queryPasajero = session.createQuery("from Pasajero WHERE id = :pasajeroId");
		queryPasajero.setParameter("pasajeroId", pasajeroId);
		Pasajero pasajero = (Pasajero) queryPasajero.uniqueResult();
		
		// Las validaciones previas al agregar el comentario se realizaron en el service
		Comentario c = new Comentario (puntaje, comentario, pasajero);
		viaje.addComentario(c);

		session.save("Comentario", c);
		session.flush(); 
		session.disconnect();
		session.close();
	}

	@Override
	public void finalizarViaje(Integer viajeId) {
		Session session = this.getSession();
	
		// Las validaciones previas al finalizar el viaje se realizaron en el service
		Query query2 = session.createQuery("UPDATE Viaje SET estado = :estado WHERE id = :viajeId");
		query2.setParameter("estado", 'F');
		query2.setParameter("viajeId", viajeId);
		query2.executeUpdate();
		
		session.disconnect();
		session.close();
		
	}

	@Override
	public List<Viaje> getViajesConductor(Integer conductorId) {
		Session session = this.getSession();
		Query query = session.createQuery("from Viaje WHERE id_conductor = :conductorId");
		query.setParameter("conductorId", conductorId);
		List<Viaje> viajes = query.list();
		session.disconnect();
		session.close();		
		return viajes;
	}

	@Override
	public boolean fuePasajero(Integer pasajeroId, Integer viajeId) {
		Session session = this.getSession();
		Query query =session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje v = (Viaje) query.uniqueResult();
		for (Pasajero p : v.getPasajeros()){
			if (p.getIdUsuario() == pasajeroId){
				session.disconnect();
				session.close();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean calificoViaje(Integer pasajeroId, Integer viajeId) {
		Session session = this.getSession();
		Query query =session.createQuery("from Viaje WHERE id = :viajeId");
		query.setParameter("viajeId", viajeId);
		Viaje v = (Viaje) query.uniqueResult();
		for (Comentario c : v.getComentarios()){
			if (c.getPasajero().getIdUsuario() == pasajeroId){
				session.disconnect();
				session.close();
				return true;
			}
		}
		return false;
	}
	
	
}