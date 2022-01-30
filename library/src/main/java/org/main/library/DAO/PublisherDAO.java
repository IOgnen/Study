package org.main.library.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.library.entity.Publisher;

public class PublisherDAO {

	static SessionFactory factory;
	static Transaction tx = null;
	
	public static Publisher getPublisher(String id) {

		Session session = LibraryDAO.initDB();

		String hql = "FROM Publisher P WHERE P.id =:pid";
		Query query = session.createQuery(hql);
		query.setParameter("pid", id);

		List<Publisher> results = query.list();

		return results.get(0);

	}
	
	public static void insertPublisher(Publisher publisher) {

		Session session = LibraryDAO.initDB();

		try {

			tx = session.beginTransaction();
			session.save(publisher);
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

}
