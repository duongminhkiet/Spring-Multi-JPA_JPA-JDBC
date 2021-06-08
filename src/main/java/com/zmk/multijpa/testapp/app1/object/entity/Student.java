package com.zmk.multijpa.testapp.app1.object.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zmk.multijpa.testapp.helper.GlobalVariable;


@Entity
@Table(name=GlobalVariable.TBL_STUDENT_APP1)
public class Student { //implements Serializable{
//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	@Column(name = "name", nullable = false,columnDefinition = "nvarchar(255) default ''")
	private String name;
	
	@Column(name = "phone", nullable = true)
	private String phone;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date created = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date modified;

	public Student() {
		
	}
	public Student(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
	public Student(long id,String name, String phone, java.util.Date created, java.util.Date modified) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.created = created;
		this.modified = modified;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.util.Date getCreated() {
		return created;
	}

	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	public java.util.Date getModified() {
		return modified;
	}

	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}
}
