package info.yalamanchili.gwt.composite;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.Messages;

public abstract class ReadCompositeRef<T extends LightEntity> extends
		ReadUpdateCreateCompositeRef<T> {

	protected void initReadComposite(T entity, ConstantsWithLookup constants,
			Messages messages) {
		this.entity = entity;
		init(entity, true, constants);
	}

	protected void initReadComposite(T t, Long id,
			ConstantsWithLookup constants, Messages messages) {
		this.entityId = id;
		init(t, true, constants);
	}

	protected void postInit() {
		if (entityId != null) {
			readData(entityId);
		}
		if (entity != null) {
			readData(entity);
		}
		panel.setSpacing(5);
	}

	protected abstract void readData(Long id);

	protected abstract void readData(T entity);

	protected void postValidate() {

	}

}
