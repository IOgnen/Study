package org.main.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "titile")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Integer price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pub_id")
	private Publisher publisher;

	public Book() {
		super();
	}

	public Book(String id, String title, String author,Integer price, Integer quantity) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}	

	
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer setQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}

