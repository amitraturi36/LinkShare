package com.intelligrape.linkshare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import src.Visibility;

@Entity
@Table(name = "topic", uniqueConstraints = @UniqueConstraint(columnNames = { "topicName", "createdBy_id" }))
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String topicName;
	@Enumerated(EnumType.STRING)
	private Visibility visibility;
	@ManyToOne(cascade = CascadeType.ALL)
	private User createdBy;
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Subscription> subscriptions = new ArrayList<>();
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Resource> resources = new ArrayList<>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = Visibility.stringToEnum(visibility);
	}

	public void addSubscriptions(Subscription subscription) {
		subscriptions.add(subscription);
		subscription.setTopic(this);
	}
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public List<Resource> getResource() {
		return resources;
	}
	public void addResources(Resource resource) {
		resources.add(resource);
		resource.setTopic(this);
	}


}
