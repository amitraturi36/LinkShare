package com.intelligrape.linkshare.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "documentResource")
public class DocumentResource extends Resource {
	private String filePaths;
	@Temporal(TemporalType.DATE)
	private Date dateCreated = new Date();

	public String getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(String filePaths) {
		this.filePaths = filePaths;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

}
