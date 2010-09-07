package info.yalamanchili.commons;

import java.util.List;

public class TestEntitySuper {
	protected String superString;
	protected Integer superint;
	protected List<TestEntityChildOne> childrenOne;
	
	public String getSuperString() {
		return superString;
	}
	public void setSuperString(String superString) {
		this.superString = superString;
	}
	public Integer getSuperint() {
		return superint;
	}
	public void setSuperint(Integer superint) {
		this.superint = superint;
	}
	public List<TestEntityChildOne> getChildrenOne() {
		return childrenOne;
	}
	public void setChildrenOne(List<TestEntityChildOne> childrenOne) {
		this.childrenOne = childrenOne;
	}

}
