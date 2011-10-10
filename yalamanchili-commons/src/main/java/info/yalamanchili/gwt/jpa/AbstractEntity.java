package info.yalamanchili.gwt.jpa;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import net.sf.gilead.pojo.gwt.LightEntity;

/**
 * Default Parent/root entity for all jpa entities to serve id,version
 * properties and extends from LightEntity to suppport GWT-GILEAD
 * 
 * @author ayalamanchili
 */
//TODO move to non gwt package since not needed with request factory
@XmlType
@MappedSuperclass
public abstract class AbstractEntity extends LightEntity implements
		Serializable {

	@XmlAttribute
	@Version
	private Integer version;

	@XmlElement
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}
}
