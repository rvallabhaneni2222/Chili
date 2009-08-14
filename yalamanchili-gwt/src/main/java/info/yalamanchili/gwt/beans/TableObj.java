package info.yalamanchili.gwt.beans;

import java.util.List;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TableObj<T extends LightEntity> implements IsSerializable {
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
