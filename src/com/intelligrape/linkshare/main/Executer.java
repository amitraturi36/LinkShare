package com.intelligrape.linkshare.main;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.intelligrape.linkshare.dao.DocumentResource;
import com.intelligrape.linkshare.dao.LinkResource;
import com.intelligrape.linkshare.dao.Subscription;
import com.intelligrape.linkshare.dao.Topic;
import com.intelligrape.linkshare.dao.User;

public class Executer {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("resource/Hibernate.cfg.xml").build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		Session sns = sessionFactory.openSession();
		Transaction trn = sns.beginTransaction();
		Executer exc = new Executer();
		exc.insertUser(sns);
		User createdBy = sns.get(User.class, new Long(1));
		exc.insertTopic(createdBy,sns);
		trn.commit();
		sns.close();
		sns = sessionFactory.openSession();
		exc.addResources(sns);
		sns.close();
		sns = sessionFactory.openSession();
		List <User> subscribedUsers=Subscription.subscribedUsers(sns,sns.get(Topic.class,new Long(1)));
		for(User user:subscribedUsers)
		{
			System.out.println("Subscribed User Name  "+user.getName());
		}
System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"); 
	}

	void insertUser(Session sns) {
		User user = new User();
		user.setFirstName("siddarth");
		user.setLastName("verma");
		user.setEmail("sidvrm@gmail.com");
		user.setPassword("12345678");
		user.setConfirmPassword("12345678");
		user.setActive(true);
		user.setAdmin(false);
		user.setUsername("sd4");
		sns.save(user);
	}

	void insertTopic(User createdBy,Session sns) {
		
		Topic topic = new Topic();
		topic.setTopicName("ek omkar");
		topic.setVisibility("PRIVATE");
		createdBy.addTopics(topic);
		subscribeUser(createdBy,topic,"serious",sns);
		sns.save(topic);
	}

	void subscribeUser(User user, Topic topic, String seriousness, Session sns) {
		Subscription subscription = new Subscription();
		subscription.setSeriousness(seriousness);
		subscription.setUser(user);
		subscription.setTopic(topic);
		sns.save(subscription);
	}

	void addResources(Session sns) {
		LinkResource linkresource;
		DocumentResource documentResource;
		String hql = "FROM User";
		Query query = sns.createQuery(hql);
		List<User> userResults = query.getResultList();
		hql = "From Topic";
		query = sns.createQuery(hql);
		List<Topic> topicResult = query.getResultList();
		Transaction trn = sns.beginTransaction();
		for (Topic topic : topicResult) {
			for (User createdBy : userResults) {
				linkresource = new LinkResource();
				linkresource.setCreatedBy(createdBy);
				linkresource.setTopic(topic);
				linkresource.setDescription(String.format("Link Resource created by %s", createdBy.getName()));
				linkresource.setUrl("http://123.com");
				sns.save(linkresource);
				documentResource = new DocumentResource();
				documentResource.setCreatedBy(createdBy);
				documentResource.setFilePaths("file://a/b/c.pdf");
				documentResource.setTopic(topic);
				documentResource.setDescription(String.format("Link Resource created by %s", createdBy.getName()));
				sns.save(documentResource);
				trn.commit();

			}

		}

	}
	
	

}
