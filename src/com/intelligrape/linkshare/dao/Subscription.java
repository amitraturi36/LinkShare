package com.intelligrape.linkshare.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import src.Seriousness;

@Entity
@Table(name = "subscription")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	private Topic topic;
	@Enumerated(EnumType.STRING)
	private Seriousness seriousness;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Seriousness getSeriousness() {
		return seriousness;
	}

	public void setSeriousness(String seriousness) {
		this.seriousness = Seriousness.stringToEnum(seriousness);
	}
public static List <User> subscribedUsers(Session session,Topic topic){
	Criteria cr = session.createCriteria(Subscription.class);
	
	ProjectionList p1=Projections.projectionList();
	  p1.add(Projections.property("user"));
	  cr.setProjection(p1);		
	cr.add(Restrictions.eq("topic",topic));
	
	List <User>userList=cr.list();
	return userList;
	
}
}
