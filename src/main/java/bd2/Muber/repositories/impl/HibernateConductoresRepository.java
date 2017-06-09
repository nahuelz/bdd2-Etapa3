package bd2.Muber.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dto.ConductorDTO;

import bd2.Muber.model.*;
import bd2.Muber.repositories.ConductoresRepositoryBI;

public class HibernateConductoresRepository extends BaseHibernateRepository implements ConductoresRepositoryBI{	
		
	public ConductorDTO getConductor(Integer conductorId){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Query query =session.createQuery("from Conductor WHERE id_usuario = :conductorId");
		query.setParameter("conductorId", conductorId);
		Conductor conductor = (Conductor) query.uniqueResult();
		ConductorDTO conductorDTO = new ConductorDTO();
		if (conductor != null){
			conductorDTO = new ConductorDTO(conductor);
		}
		tx.rollback();
		session.disconnect();
		session.close();
		return conductorDTO;
	}
	
	public List<ConductorDTO> getConductores(){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		List<Conductor> conductores = session.createQuery("from Conductor").list();
		List<ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();
		for (Conductor c : conductores) {
			ConductorDTO con = new ConductorDTO(c);
			conductoresDTO.add(con);
		}
		tx.rollback();
		session.disconnect();
		session.close();		
		return conductoresDTO;
	}
	
	public List<ConductorDTO> obtenerTop10(){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		List<Conductor> conductores = session.createQuery("from Conductor c where c not in (select v.Conductor from Viaje v where v.estado = 'A')").list();
		conductores.sort((c1, c2) -> c2.puntajePromedio().compareTo(c1.puntajePromedio()));
		conductores = conductores.subList(0, Integer.min(conductores.size(), 10));
		List<ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();
		for (Conductor c : conductores) {
			ConductorDTO con = new ConductorDTO(c);
			con.setPuntajePromedio(c.puntajePromedio());
			conductoresDTO.add(con);
		}
		tx.rollback();
		session.disconnect();
		session.close();
		return conductoresDTO;
	}

	
	
}