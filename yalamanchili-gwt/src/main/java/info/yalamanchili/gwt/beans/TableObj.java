package info.yalamanchili.gwt.beans;

import java.io.Serializable;
import java.util.List;

//TODO does this class need to extend AbstractEntity?
public class TableObj<T extends Serializable> implements Serializable {
	protected Long numberOfRecords;
	protected List<T> records;

	public Long getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

}
