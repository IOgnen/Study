package org.main.library.entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "Members")
public class Member {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "adress")
	private String address;

	@Column(name = "initDate")
	private Date initDate;

	@Column(name = "expireDate")
	private Date expireDate;

	public Member() {
		super();
	}

	public Member(String id, String name, String address, Date initDate, Date expireDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.initDate = initDate;
		this.expireDate = expireDate;
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

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {

		this.expireDate = expireDate;
	}
	
	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

}
