package info.yalamanchili.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Past;
import org.hibernate.validator.Range;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	protected Long id;
	@NotEmpty
	protected String firstName;
	@NotEmpty
	protected String lastName;
	@Range(min = 0, max = 100)
	protected Integer age;
	@Range(min = 3, max = 8)
	protected Float height;
	@NotNull
	@Enumerated(EnumType.STRING)
	protected Sex sex;
	@Past
	protected Date dateOfBirth;
	@NotNull
	protected Boolean exists;

	public Person() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getExists() {
		return exists;
	}

	public void setExists(Boolean exists) {
		this.exists = exists;
	}

}
