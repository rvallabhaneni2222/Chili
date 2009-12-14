package info.yalamanchili.gwt.composite;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.Messages;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadCompositeRef.
 */
public abstract class ReadCompositeRef<T extends LightEntity> extends
		ReadUpdateCreateCompositeRef<T> {

	/**
	 * Inits the read composite.
	 * 
	 * @param entity
	 *            the entity
	 * @param constants
	 *            the constants
	 * @param messages
	 *            the messages
	 */
	protected void initReadComposite(T entity, ConstantsWithLookup constants,
			Messages messages) {
		this.entity = entity;
		init(entity, true, constants);
		entityCaptionPanel.addStyleName("readEntityCaptionPanel");
		entityDisplayWidget.addStyleName("readEntityDisplayWidget");
		basePanel.addStyleName("readBasePanel");	
	}

	/**
	 * Inits the read composite.
	 * 
	 * @param t
	 *            the t
	 * @param id
	 *            the id
	 * @param constants
	 *            the constants
	 * @param messages
	 *            the messages
	 */
	protected void initReadComposite(T t, Long id,
			ConstantsWithLookup constants, Messages messages) {
		this.entityId = id;
		init(t, true, constants);
		entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-ReadEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-ReadBasePanel");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.yalamanchili.gwt.composite.ReadUpdateCreateCompositeRef#postInit()
	 */
	protected void postInit() {
		if (entityId != null) {
			readData(entityId);
		}
		if (entity != null) {
			readData(entity);
		}
		entityDisplayWidget.setSpacing(5);
	}

	/**
	 * Read data.
	 * 
	 * @param id
	 *            the id
	 */
	protected abstract void readData(Long id);

	/**
	 * Read data.
	 * 
	 * @param entity
	 *            the entity
	 */
	protected abstract void readData(T entity);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.yalamanchili.gwt.composite.ReadUpdateCreateCompositeRef#postValidate
	 * ()
	 */
	protected void postValidate() {

	}

}
