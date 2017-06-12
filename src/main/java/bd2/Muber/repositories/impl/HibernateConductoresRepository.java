package bd2.Muber.repositories.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.model.*;
import bd2.Muber.repositories.ConductoresRepositoryBI;

public class HibernateConductoresRepository extends BaseHibernateRepository implements ConductoresRepositoryBI{	
		
	public Conductor getConductor(Integer conductorId){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Query query =session.createQuery("from Conductor WHERE id_usuario = :conductorId");
		query.setParameter("conductorId", conductorId);
		Conductor conductor = (Conductor) query.uniqueResult();
		tx.rollback();
		session.disconnect();
		session.close();
		return conductor;
	}
	
	public List<Conductor> getConductores(){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		List<Conductor> conductores = session.createQuery("from Conductor").list();
		tx.rollback();
		session.disconnect();
		session.close();		
		return conductores;
	}
	
	public List<Conductor> obtenerTop10(){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		List<Conductor> conductores = session.createQuery("from Conductor c where c not in (select v.Conductor from Viaje v where v.estado = 'A')").list();
		conductores.sort((c1, c2) -> c2.puntajePromedio().compareTo(c1.puntajePromedio()));
		conductores = conductores.subList(0, Integer.min(conductores.size(), 10));
		tx.rollback();
		session.disconnect();
		session.close();
		return conductores;
	}

	
	
}