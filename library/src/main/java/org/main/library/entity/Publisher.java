package org.main.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher {

	@Id
	@Column(name = "pub_id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "number")
	private String number;
	
	public Publisher() {
		super();
	}

	public Publisher(String id, String name, String address, String number) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

