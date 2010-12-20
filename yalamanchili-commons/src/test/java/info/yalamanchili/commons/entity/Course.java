package info.yalamanchili.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Course implements Serializable {

	private static final long serialVersionUID = -5954372149347205886L;

	@Id
	@GeneratedValue
	protected Long id;
	@NotNull
	@Size(min = 1)
	protected String name;
	@Future
	protected Date startDate;
	@Past
	protected Date endDate;
	@DecimalMax(value = "5.0")
	@DecimalMin("0.0")
	protected Float weight;
	@Max(60)
	@Min(0)
	protected Integer noOfStuents;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Integer getNoOfStuents() {
		return noOfStuents;
	}

	public void setNoOfStuents(Integer noOfStuents) {
		this.noOfStuents = noOfStuents;
	}

}
