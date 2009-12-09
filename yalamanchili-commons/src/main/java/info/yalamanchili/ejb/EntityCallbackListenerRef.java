package info.yalamanchili.ejb;

import info.yalamanchili.commons.ReflectionUtils;

import java.util.Map;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EntityCallbackListenerRef {
	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(EntityCallbackListener.class);

	/**
	 * Pre insert.
	 * 
	 * @param entity
	 *            the entity
	 */
	@PrePersist
	public void preInsert(Object entity) {
		log.debug("pre insert:" + entity.getClass().getCanonicalName());
		Map<String, Object> f = ReflectionUtils.getFieldsDataFromEntity(entity,
				entity.getClass());

		for (String str : f.keySet()) {
			log.debug(str + ":" + f.get(str));
		}
	}

	/**
	 * Post insert.
	 * 
	 * @param entity
	 *            the entity
	 */
	@PostPersist
	public void postInsert(Object entity) {
		Map<String, Object> f = ReflectionUtils.getFieldsDataFromEntity(entity,
				entity.getClass());

		for (String str : f.keySet()) {
			log.debug(str + ":" + f.get(str));
		}
	}

	/**
	 * Pre update.
	 * 
	 * @param entity
	 *            the entity
	 */
	@PreUpdate
	public void preUpdate(Object entity) {
		log.debug("pre update:" + entity.getClass().getCanonicalName());
		Map<String, Object> f = ReflectionUtils.getFieldsDataFromEntity(entity,
				entity.getClass());

		for (String str : f.keySet()) {
			log.debug(str + ":" + f.get(str));
		}
	}

	/**
	 * Post update.
	 * 
	 * @param entity
	 *            the entity
	 */
	@PostUpdate
	public void postUpdate(Object entity) {
		log.debug("post update:" + entity.getClass().getCanonicalName());
		Map<String, Object> f = ReflectionUtils.getFieldsDataFromEntity(entity,
				entity.getClass());

		for (String str : f.keySet()) {
			log.debug(str + ":" + f.get(str));
		}
	}

	/**
	 * Pre remove.
	 * 
	 * @param entity
	 *            the entity
	 */
	@PreRemove
	public void preRemove(Object entity) {
		log.debug("pre remove:" + entity.getClass().getCanonicalName());
	}

	/**
	 * Post remove.
	 * 
	 * @param entity
	 *            the entity
	 */
	@PostRemove
	public void postRemove(Object entity) {
		log.debug("post remove:" + entity.getClass().getCanonicalName());
	}

}
