package info.yalamanchili.jpa.callback;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EntityCallbackListener {
	private static final Log log = LogFactory
			.getLog(EntityCallbackListener.class);

	@PrePersist
	public void preInsert(Object entity) {
		log.debug("pre insert:" + entity.getClass().getCanonicalName());
	}

	@PostPersist
	public void postInsert(Object entity) {
		log.debug("post insert:" + entity.getClass().getCanonicalName());
	}

	@PreUpdate
	public void preUpdate(Object entity) {
		log.debug("pre update:" + entity.getClass().getCanonicalName());
	}

	@PostUpdate
	public void postUpdate(Object entity) {
		log.debug("post update:" + entity.getClass().getCanonicalName());
	}

	@PreRemove
	public void preRemove(Object entity) {
		log.debug("pre remove:" + entity.getClass().getCanonicalName());
	}

	@PostRemove
	public void postRemove(Object entity) {
		log.debug("post remove:" + entity.getClass().getCanonicalName());
	}

}
