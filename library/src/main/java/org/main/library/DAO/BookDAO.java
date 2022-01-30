package org.main.library.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.library.entity.Book;
import org.main.library.entity.Member;

public class BookDAO {

	static SessionFactory factory;
	static Transaction tx = null;
	
	public static void insertBook(String id, Book book) {
		Session session = LibraryDAO.initDB();

		try {

			tx = session.beginTransaction();

			book.setPublisher(PublisherDAO.getPublisher(id));
			session.save(book);

			tx.commit();
			session.close();

		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	public static Book getBook(String id) {

		Session session = LibraryDAO.initDB();

		String hql = "FROM Book B WHERE B.id =:bid";
		Query query = session.createQuery(hql);
		query.setParameter("bid", id);

		List<Book> results = query.list();

		return results.get(0);

	}
	
	public static List<Book> getBooks() {

		Session session = LibraryDAO.initDB();

		try {

			String hql = "FROM Book B";
			Query query = session.createQuery(hql);

			List<Book> books = query.list();

			return books;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void updateBook(Book book) {
		
		Session session = LibraryDAO.initDB();
		
		try {
			
			tx=session.beginTransaction();
			session.update(book);
			tx.commit();
			session.close();
			
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	
}
