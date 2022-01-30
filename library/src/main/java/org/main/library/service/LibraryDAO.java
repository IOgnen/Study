package org.main.library.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.main.library.entity.*;

public class LibraryDAO {

	static SessionFactory factory;
	static Transaction tx = null;

	public static Session initDB() {

		Session session;
		Configuration cfg = new Configuration();

		try {

			cfg.addAnnotatedClass(org.main.library.entity.Publisher.class);
			cfg.addAnnotatedClass(org.main.library.entity.Book.class);
			cfg.addAnnotatedClass(org.main.library.entity.Member.class);
			cfg.addAnnotatedClass(org.main.library.entity.Borrows.class);
			cfg.configure();

			factory = cfg.configure().buildSessionFactory();

			session = factory.openSession();

			return session;

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void insertMember(Member member) {

		Session session = initDB();

		try {

			tx = session.beginTransaction();
			session.save(member);
			tx.commit();
			session.close();

		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}

	}

	public static void insertPublisher(Publisher publisher) {

		Session session = initDB();

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

	public static void insertBook(String id, Book book) {
		Session session = initDB();

		try {

			tx = session.beginTransaction();

			book.setPublisher(getPublisher(id));
			session.save(book);

			tx.commit();
			session.close();

		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	public static void insertBorrows(BorrowsDTO borrowsDTO) {
		
		Session session = initDB();

		Book book = getBook(borrowsDTO.getBookId());
		Member member = getMember(borrowsDTO.getMemberId());

		try {

			tx = session.beginTransaction();

			long time = System.currentTimeMillis();
			Date initDate = new Date(time);
			Date expireDate = new Date(time);

			expireDate.setDate(initDate.getDay() + 14);

			Borrows borrows = new Borrows(member, book);
			borrows.setIssue(initDate);
			borrows.setDueDate(expireDate);
			borrows.setReturned(false);

			book.setQuantity(book.getQuantity() - 1);

			session.save(borrows);
			session.update(book);

			tx.commit();
			session.close();

		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	public static Book getBook(String id) {

		Session session = initDB();

		String hql = "FROM Book B WHERE B.id =:bid";
		Query query = session.createQuery(hql);
		query.setParameter("bid", id);

		List<Book> results = query.list();

		return results.get(0);

	}

	public static Member getMember(String id) {

		Session session = initDB();

		String hql = "FROM Member M WHERE M.id =:mid";
		Query query = session.createQuery(hql);
		query.setParameter("mid", id);

		List<Member> results = query.list();

		return results.get(0);

	}

	public static Publisher getPublisher(String id) {

		Session session = initDB();

		String hql = "FROM Publisher P WHERE P.id =:pid";
		Query query = session.createQuery(hql);
		query.setParameter("pid", id);

		List<Publisher> results = query.list();

		return results.get(0);

	}

	public static List<Book> getBooks() {

		Session session = initDB();

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
	
	public static List<Member> getMembers(){
		
		Session session = initDB();
		
		try {
			
			String hql = "FROM Member M";
			Query query = session.createQuery(hql);
			
			List<Member> members = query.list();
			
			return members;
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void insertReturns(BorrowsDTO borrowsDTO) {
		
		Session session = initDB();
		Book book = getBook(borrowsDTO.getBookId());
		Borrows borrows = getBorrows(borrowsDTO.getMemberId());
		
		try {
			
			tx = session.beginTransaction();		
			
			book.setQuantity(book.getQuantity() + 1);		
			updateBook(book);
			
			borrows.setReturned(true);
			updateBorrows(borrows);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		
	}
	
	public static void updateBook(Book book) {
		
		Session session = initDB();
		
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
	
	public static void updateBorrows(Borrows borrows) {
		
		Session session = initDB();
		
		try {
			
			tx=session.beginTransaction();
			session.update(borrows);
			tx.commit();
			session.close();
			
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	public static Borrows getBorrows(String id) {
		
		Session session = initDB();
		
		List<Borrows> result = session.createNativeQuery("SELECT * FROM Borrows WHERE member_id='" + id + "';", Borrows.class).list();

		return result.get(0);
		
	}
	
}
