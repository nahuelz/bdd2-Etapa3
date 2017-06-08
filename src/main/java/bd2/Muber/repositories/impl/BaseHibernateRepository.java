package bd2.Muber.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseHibernateRepository{
	SessionFactory sessionFactory;
	
	public BaseHibernateRepository(){
	
	}
	
	public BaseHibernateRepository(SessionFactory session){
		sessionFactory = session;
	}
	
	public void setSessionFactory(SessionFactory session){
		this.sessionFactory = session;
	}
	
	public Session getSession() {
		//Configuration cfg = new Configuration();
		//cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = sessionFactory;
		Session session = factory.openSession();
		return session;
	        
	}
	
	
	
		
}