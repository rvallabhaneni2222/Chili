package info.yalamanchili.gwt.composite;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.google.gwt.i18n.client.ConstantsWithLookup;

public abstract class ReadComposite<T extends LightEntity> extends
		ReadUpdateCreateComposite<T> {

	protected void initReadComposite(T entity, String className,
			final ConstantsWithLookup constants) {
		init(className, true, constants);
		entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-ReadEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-ReadBasePanel");
		readData(entity);
	}

	protected void initReadComposite(Long id, String className,
			final ConstantsWithLookup constants) {
		init(className, true, constants);
		entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-ReadEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-ReadBasePanel");
		readData(id);
	}

	protected abstract void readData(Long id);

	protected abstract void readData(T entity);

}