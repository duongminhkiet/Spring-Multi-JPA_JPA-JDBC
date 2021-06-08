package com.zmk.multijpa.testapp.app2.object.entity;

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
@Table(name=GlobalVariable.TBL_RULE_APP2)
public class Rule implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	@Column(name = "TEN_THUOC", nullable = false,columnDefinition = "nvarchar(255) default ''")
	private String name;
	
	@Column(name = "DescripTION_X", nullable = true, columnDefinition = "ntext default ''")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date created = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date modified;
	
	public Rule() {
		
	}
	public Rule(String name, String description) {
		this.name = name;
		this.description = description;
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
