package bd2.Muber;

import java.util.Date;
import java.util.Calendar;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import bd2.Muber.model.Comentario;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Muber;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;

public class App {

	public static void main(String[] args) {
		System.out.println("----------------------- Setting up Hibernate -----------------------");
		Configuration cfg = new Configuration();
		cfg.configure("../hibernate.cfg.xml");
		
		System.out.println("Droping schema.........");
		//new SchemaExport(cfg).drop(true, true);
		System.out.println("DONE.");
		
		System.out.println("Generating schema.........");
		//new SchemaExport(cfg).create(true, true);
		System.out.println("DONE.");

		System.out.println("Building sessions.........");
		
		SessionFactory sf = cfg.buildSessionFactory();
		
		Calendar c = Calendar.getInstance();
		c.set(2018, 0, 1);
		Conductor conductor = new Conductor("Roberto", "123456", new Date(), c.getTime());
		
		
		c.set(2017, 6, 5);
		
		
		Viaje viaje = new Viaje("La Plata", "Tres Arroyos", 900, 10, c.getTime(), conductor);
		
		Pasajero pasajero1 = new Pasajero("Germ�n", "123456", new Date(), 1500);
		Pasajero pasajero2 = new Pasajero("Alicia", "123456", new Date(), 1500);
		Pasajero pasajero3 = new Pasajero("Margarita", "123456", new Date(), 1500);
		
		viaje.addPasajero(pasajero1);
		viaje.addPasajero(pasajero2);
		viaje.addPasajero(pasajero3);
		
		viaje.cerrar();
		viaje.finalizar();

		
		Comentario comentario1 = new Comentario(5, "Muy buen conductor", pasajero1);
		viaje.addComentario(comentario1);
		
		Comentario comentario2 = new Comentario(4, "Buen conductor", pasajero2);
		viaje.addComentario(comentario2);
		
		Comentario comentario3 = new Comentario(4, "Buen conductor", pasajero3);
		viaje.addComentario(comentario3);
		
		Viaje viaje2 = new Viaje("Cordoba", "Mar del plata", 3500, 10, c.getTime(), conductor);
		
		Pasajero pasajero4 = new Pasajero("Hugo", "123456", new Date(), 2300);
		
		pasajero3.cargarCredito(4000.0);
		viaje2.addPasajero(pasajero3);
		viaje2.addPasajero(pasajero4);
		
		viaje2.cerrar();
		viaje2.finalizar();
		
		Comentario comentario4 = new Comentario(4, "Buen conductor", pasajero3);
		viaje2.addComentario(comentario4);
		Comentario comentario5 = new Comentario(1, "Mal conductor", pasajero4);
		viaje2.addComentario(comentario5);
		
		Muber muber = new Muber();
		muber.addViaje(viaje);
		muber.addViaje(viaje2);
		muber.addUser(conductor);
		muber.addUser(pasajero1);
		muber.addUser(pasajero2);
		muber.addUser(pasajero3);
		muber.addUser(pasajero4);
		
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		session.persist(muber);
		tx.commit();
		 	
	}

}
