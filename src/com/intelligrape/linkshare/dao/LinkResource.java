package com.intelligrape.linkshare.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.URL;
@Entity(name = "linkResource")
public class LinkResource extends Resource {
	@URL
	private String url;
	@Temporal(TemporalType.DATE)
	private Date dateCreated=new Date();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
}
