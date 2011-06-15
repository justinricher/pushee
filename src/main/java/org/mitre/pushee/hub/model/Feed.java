package org.mitre.pushee.hub.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Feed representation. Feed objects contain a collection of the subscribers
 * subscribed to this feed, as well as a reference back to the feed's 
 * Publisher.
 * 
 * @author AANGANES
 *
 */
@Entity
@Table(name="feed")
@NamedQueries({
        @NamedQuery(name = "Feed.getByUrl", query = "select f from Feed f where f.url = :feedUrl"),
        @NamedQuery(name = "Feed.getAll", query = "select f from Feed f")
})
public class Feed {

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Feed other = (Feed) obj;
		if (type != other.type) {
			return false;
		}
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
			return false;
		}
		return true;
	}

	public enum FeedType {
		ATOM, RSS
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Basic
	private String url;
	
	@Basic
	private FeedType type;
	
	@ManyToOne
    @JoinColumn(name="publisher_id")
	private Publisher publisher;
	
	@OneToMany(mappedBy = "feed")
	private Collection<Subscription> subscriptions;
	
	public Feed() {
		subscriptions = new ArrayList<Subscription>();
	}
	
	/**
	 * Add a subscriber to the Collection
	 * 
	 * @param s the subscriber to add
	 */
	public void addSubscription(Subscription s) {
		
		subscriptions.add(s); 
	
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
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(FeedType type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public FeedType getType() {
		return type;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}
	
	/**
	 * @return the subscriptions Collection
	 */
	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
	/**
	 * @param subscriptions the subscriptions Collection to set
	 */
	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
}
