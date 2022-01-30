package org.main.library.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.main.library.entity.Member;

public class MemberDAO {

	static SessionFactory factory;
	static Transaction tx = null;
	
	public static void insertMember(Member member) {

		Session session = LibraryDAO.initDB();

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
	public static Member getMember(String id) {

		Session session = LibraryDAO.initDB();

		String hql = "FROM Member M WHERE M.id =:mid";
		Query query = session.createQuery(hql);
		query.setParameter("mid", id);

		List<Member> results = query.list();

		return results.get(0);

	}
	public static List<Member> getMembers(){
		
		Session session = LibraryDAO.initDB();
		
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
	
}
