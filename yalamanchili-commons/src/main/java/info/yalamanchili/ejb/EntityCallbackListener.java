package info.yalamanchili.ejb;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving entityCallback events.
 * The class that is interested in processing a entityCallback
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addEntityCallbackListener<code> method. When
 * the entityCallback event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see EntityCallbackEvent
 */
public class EntityCallbackListener {
	
	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(EntityCallbackListener.class);

	/**
	 * Pre insert.
	 * 
	 * @param entity the entity
	 */
	@PrePersist
	public void preInsert(Object entity) {
		log.debug("pre insert:" + entity.getClass().getCanonicalName());
	}

	/**
	 * Post insert.
	 * 
	 * @param entity the entity
	 */
	@PostPersist
	public void postInsert(Object entity) {
		log.debug("post insert:" + entity.getClass().getCanonicalName());
	}

	/**
	 * Pre update.
	 * 
	 * @param entity the entity
	 */
	@PreUpdate
	public void preUpdate(Object entity) {
		log.debug("pre update:" + entity.getClass().getCanonicalName());
	}

	/**
	 * Post update.
	 * 
	 * @param entity the entity
	 */
	@PostUpdate
	public void postUpdate(Object entity) {
		log.debug("post update:" + entity.getClass().getCanonicalName());
	}

	/**
	 * Pre remove.
	 * 
	 * @param entity the entity
	 */
	@PreRemove
	public void preRemove(Object entity) {
		log.debug("pre remove:" + entity.getClass().getCanonicalName());
	}

	/**
	 * Post remove.
	 * 
	 * @param entity the entity
	 */
	@PostRemove
	public void postRemove(Object entity) {
		log.debug("post remove:" + entity.getClass().getCanonicalName());
	}

}
