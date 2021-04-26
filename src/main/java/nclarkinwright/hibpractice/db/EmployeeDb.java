package nclarkinwright.hibpractice.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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

	public List<Employee> query(String search) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		String hql = "FROM Employee e WHERE e.firstName=?1 OR e.lastName=?2 OR e.company=?3";
		Query query = session.createQuery(hql);
		query.setParameter(1, search);
		query.setParameter(2, search);
		query.setParameter(3, search);
		List<Employee> employees = query.getResultList();
		
		session.getTransaction().commit();
		
		return employees;
	}
	
	
}
