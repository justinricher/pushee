package org.mitre.pushee.hub.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Subscriber representation. Subscribers are referenced from
 * the Feeds they are subscribed to.
 * @author AANGANES
 *
 */
@Entity
public class Subscriber {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Basic
	private String postbackURL;
	
    @OneToMany
    private Collection<Subscription> subscriptions;
    
	public Subscriber() {
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

    /**
	 * @param postbackURL the postbackURL to set
	 */
	public void setPostbackURL(String postbackURL) {
		this.postbackURL = postbackURL;
	}

	/**
	 * @return the postbackURL
	 */
	public String getPostbackURL() {
		return postbackURL;
	}

	/**
	 * @param subscriptions the subscriptions to set
	 */
	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	/**
	 * @return the subscriptions
	 */
	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
}
