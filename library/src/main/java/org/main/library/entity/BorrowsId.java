package org.main.library.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BorrowsId implements Serializable {

	@Column(name = "memberId")
	private String memberId;
	
	@Column(name = "bookId")
	private String bookId;
	
	private BorrowsId() {
		super();
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

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public BorrowsId(String memberId, String bookId) {
		this.memberId=memberId;
		this.bookId=bookId;
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        BorrowsId that = (BorrowsId) o;
        return Objects.equals(memberId, that.memberId) &&
               Objects.equals(bookId, that.bookId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(memberId, bookId);
    }
	
}

