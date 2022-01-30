package org.main.library.entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "Borrows")
public class Borrows {

	@EmbeddedId
	private BorrowsId borrowsId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("memberId")
	private Member member;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("bookId")
	private Book book;
	
	@Column(name  = "dueDate")
	private Date dueDate;
	
	@Column(name = "issueDate")
	private Date issueDate;
	
	@Column(name = "returned")
	private boolean returned;
	
	public BorrowsId getBorrowsId() {
		return borrowsId;
	}

	public void setBorrowsId(BorrowsId borrowsId) {
		this.borrowsId = borrowsId;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Borrows() {
		super();
	}

	public Borrows(Member member, Book book) {
		super();
		this.member=member;
		this.book=book;
		this.borrowsId = new BorrowsId(member.getId(),book.getId());
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssue(Date issue) {
		this.issueDate = issue;
	}	
	
}


