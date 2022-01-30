package org.main.library.entity;

public class BorrowsDTO {

	private String memberId;
	private String bookId;
	
	public BorrowsDTO() {
		super();
	}

	public BorrowsDTO(String memberId, String bookId) {
		this.bookId = bookId;
		this.memberId = memberId;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookid(String bookId) {
		this.bookId = bookId;
	}	
	
}
