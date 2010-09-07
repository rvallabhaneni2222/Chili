package info.yalamanchili.commons;

import java.util.Date;
import java.util.Set;

public class TestEntity extends TestEntitySuper {
	protected Long longField;
	protected String stringField;
	protected Date dateField;
	protected Integer integerField;
	protected Float floatField;
	protected Boolean booleanField;
	protected Set<TestEntityChildTwo> childrenTwo;
	protected TestEnum testenum;

	public Long getLongField() {
		return longField;
	}

	public void setLongField(Long longField) {
		this.longField = longField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public Date getDateField() {
		return dateField;
	}

	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}

	public Integer getIntegerField() {
		return integerField;
	}

	public void setIntegerField(Integer integerField) {
		this.integerField = integerField;
	}

	public Float getFloatField() {
		return floatField;
	}

	public void setFloatField(Float floatField) {
		this.floatField = floatField;
	}

	public Boolean getBooleanField() {
		return booleanField;
	}

	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}

	public Set<TestEntityChildTwo> getChildrenTwo() {
		return childrenTwo;
	}

	public void setChildrenTwo(Set<TestEntityChildTwo> childrenTwo) {
		this.childrenTwo = childrenTwo;
	}

	public TestEnum getTestenum() {
		return testenum;
	}

	public void setTestenum(TestEnum testenum) {
		this.testenum = testenum;
	}

}
