package info.yalamanchili.gwt.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * this class holds the data required to popuplate multiListBox available is the
 * list of available items to select selected contains the ids already selected
 */
// TODO does this class need to extend Serializable or lightentity?
// need to check the serializable code generated
public class MultiSelectObj<T extends Serializable> implements Serializable {
	protected Map<Long, String> available;
	// TODO may need to remove the ids from this and just user the selected objs
	// and get ids on the clinet
	protected Set<Long> selected;
	protected List<T> selectedObjs;

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

	public List<T> getSelectedObjs() {
		return selectedObjs;
	}

	public void setSelectedObjs(List<T> selectedObjs) {
		this.selectedObjs = selectedObjs;
	}

}
