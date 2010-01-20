package info.yalamanchili.gwt.holders;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TableObj<T extends Serializable> implements IsSerializable {
	Integer noOfRecords;
	List<T> records;
}
