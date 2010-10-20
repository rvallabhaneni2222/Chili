package info.yalamanchili.gwt.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.NotEmpty;

@Entity
public class Student extends Person {

	private static final long serialVersionUID = 1L;
	@NotEmpty
	protected String sid;
	protected Date startDate;

	public Student() {

	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
