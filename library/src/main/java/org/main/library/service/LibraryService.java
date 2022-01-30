package org.main.library.service;

import java.sql.Date;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.main.library.entity.*;

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
			LibraryDAO.insertMember(member);
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
			LibraryDAO.insertPublisher(publisher);
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
			LibraryDAO.insertBook(id,book);
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
			
			return "Member : " + LibraryDAO.getMember(borrowsDTO.getMemberId()).getName() + " returned the book: " + LibraryDAO.getBook(borrowsDTO.getBookId()).getTitle() ;			
			
		} catch (Exception e) {
			return "Error";
		}
	}
	
	@GET
	@Path("/getAllBooks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getAllBooks (){
		
		List<Book> books = LibraryDAO.getBooks();
		
		return books;
		
	}

	@GET
	@Path("/getAllMembers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Member> getAllMembers(){
		
		List<Member> members = LibraryDAO.getMembers();
		
		return members;
		
	}
	
}
