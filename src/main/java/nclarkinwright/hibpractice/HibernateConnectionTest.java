package nclarkinwright.hibpractice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionTest {

	public static void main(String[] args) {
		// Create session factory
				SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				
				System.out.println("Is connected: " + session.toString());
				
				factory.close();

	}

}
