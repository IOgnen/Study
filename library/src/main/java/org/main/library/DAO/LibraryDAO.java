package org.main.library.DAO;

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

	public static void insertBorrows(BorrowsDTO borrowsDTO) {
		
		Session session = initDB();

		Book book = BookDAO.getBook(borrowsDTO.getBookId());
		Member member = MemberDAO.getMember(borrowsDTO.getMemberId());

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

	public static void insertReturns(BorrowsDTO borrowsDTO) {
		
		Session session = initDB();
		Book book = BookDAO.getBook(borrowsDTO.getBookId());
		Borrows borrows = getBorrows(borrowsDTO.getMemberId());
		
		try {
			
			tx = session.beginTransaction();		
			
			book.setQuantity(book.getQuantity() + 1);		
			BookDAO.updateBook(book);
			
			borrows.setReturned(true);
			updateBorrows(borrows);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
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
