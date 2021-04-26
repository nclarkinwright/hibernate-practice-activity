package nclarkinwright.hibpractice.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nclarkinwright.hibpractice.domain.Employee;

public class EmployeeDb {
	
	private static SessionFactory factory; 
	
	public EmployeeDb(String hibernateXmlPath) {
		if (factory == null) {
			factory = new Configuration()
					.configure(hibernateXmlPath)
					.addAnnotatedClass(Employee.class)
					.buildSessionFactory();
		}
	}
	
	public void close() {
		factory.close();
	}
	
	public Employee findById(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Employee employee = session.get(Employee.class, id);
		
		session.getTransaction().commit();
		return employee;
	}
	
	public void createEntry(Employee employee) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		session.save(employee);
		
		session.getTransaction().commit();
	}
	
	
}
