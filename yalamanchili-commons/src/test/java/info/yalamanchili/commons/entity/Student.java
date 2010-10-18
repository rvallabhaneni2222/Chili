package info.yalamanchili.commons.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.Email;
import org.hibernate.validator.Future;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
public class Student extends Person {

	private static final long serialVersionUID = 1L;
	@Length(min = 4, max = 4)
	protected String sid;
	@Future
	@NotNull
	protected Date graduationDate;
	@Email
	protected String email;

	public Student() {

	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
