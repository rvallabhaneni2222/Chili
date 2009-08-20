package info.yalamanchili.gwt.holders;

import java.util.List;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TableObj<T extends LightEntity> implements IsSerializable {
	Integer noOfRecords;
	List<T> records;
}
