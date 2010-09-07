package info.yalamanchili.commons;

import java.util.Date;

public class TestEntityChildOne {
	protected Long id;
	protected String childOneName;
	protected Date childDate;
	protected TestEntityChildOne childOne;

	public TestEntityChildOne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChildOneName() {
		return childOneName;
	}

	public void setChildOneName(String childOneName) {
		this.childOneName = childOneName;
	}

	public Date getChildDate() {
		return childDate;
	}

	public void setChildDate(Date childDate) {
		this.childDate = childDate;
	}

	public TestEntityChildOne getChildOne() {
		return childOne;
	}

	public void setChildOne(TestEntityChildOne childOne) {
		this.childOne = childOne;
	}

}
