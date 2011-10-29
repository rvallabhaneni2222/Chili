package info.yalamanchili.gwt.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class MultiSelectObjy<T extends Serializable> implements Serializable {
	protected Map<Long, String> available;

	protected Set<Long> selected;

	public Map<Long, String> getAvailable() {
		return available;
	}

	public void setAvailable(Map<Long, String> available) {
		this.available = available;
	}

	public Set<Long> getSelected() {
		return selected;
	}

	public void setSelected(Set<Long> selected) {
		this.selected = selected;
	}

}
