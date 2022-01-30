package org.main.library.service;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.main.library.DAO.BookDAO;
import org.main.library.DAO.LibraryDAO;
import org.main.library.DAO.MemberDAO;
import org.main.library.DAO.PublisherDAO;
import org.main.library.entity.Book;
import org.main.library.entity.BorrowsDTO;
import org.main.library.entity.Member;
import org.main.library.entity.Publisher;

@Path("/service")
public class LibraryService{
	
	@POST
	@Path("/createMember")
	public String createMember(Member member) {
		
		long time = System.currentTimeMillis();
		Date initDate = new Date(time);
		Date expireDate = new Date(time);
		
		expireDate.setMonth(initDate.getMonth()+1);
		
		member.setInitDate(initDate);
		member.setExpireDate(expireDate);
		
		try {
			MemberDAO.insertMember(member);
			return "Member with id:" + member.getId() + " was sucessfuly inserted :)";
		}catch(Exception e) {
			return "Member with id:" + member.getId() + " was NOT sucessfuly inserted :(";
		}
		
	}

	@POST
	@Path("/createPublisher")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createPublisher(Publisher publisher) {
		
		try {
			PublisherDAO.insertPublisher(publisher);
			return "Member with id:" + publisher.getId() + " was sucessfuly inserted :)";
		}catch(Exception e) {
			return "Member with id:" + publisher.getId() + " was NOT sucessfuly inserted :(";
		}
	}
	
	@POST
	@Path("/createBook")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createBook(@QueryParam("id") String id,Book book) {
		
		try {
			BookDAO.insertBook(id,book);
			return "Member with id:" + book.getId() + " was sucessfuly inserted :)";
		}catch(Exception e) {
			return "Member with id:" + book.getId() + " was NOT sucessfuly inserted :(";
		}
	}
	
	@POST
	@Path("/createBorrows")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createBorrows(BorrowsDTO borrowsDTO) {
		
		try {
			
			LibraryDAO.insertBorrows(borrowsDTO);
			
			return "Member id: " + borrowsDTO.getMemberId() + " borrowed the book: " + borrowsDTO.getBookId() ;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return borrowsDTO.getBookId() + "       " + borrowsDTO.getMemberId();
			
	}

	@POST
	@Path("/createReturns")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createReturns(BorrowsDTO borrowsDTO) {
		try {
			
			LibraryDAO.insertReturns(borrowsDTO);
			
			return "Member : " + MemberDAO.getMember(borrowsDTO.getMemberId()).getName() + " returned the book: " + BookDAO.getBook(borrowsDTO.getBookId()).getTitle() ;			
			
		} catch (Exception e) {
			return "Error";
		}
	}
	
	@GET
	@Path("/getAllBooks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getAllBooks (){
		
		List<Book> books = BookDAO.getBooks();
		
		return books;
		
	}

	@GET
	@Path("/getAllMembers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Member> getAllMembers(){
		
		List<Member> members = MemberDAO.getMembers();
		
		return members;
		
	}
	
}
