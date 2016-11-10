package com.intelligrape.linkshare.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity(name = "resource")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	private User createdBy;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	private Topic topic;
	
	
	public Long getId() {
		return id;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	

}
