package info.yalamanchili.security.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import net.sf.gilead.pojo.gwt.LightEntity;

import org.jboss.seam.annotations.security.management.RoleGroups;
import org.jboss.seam.annotations.security.management.RoleName;

@Entity
public class YRole extends LightEntity {

	private static final long serialVersionUID = 5472657332417332843L;
	private Integer roleId;
	private String rolename;
	private Set<YRole> groups;

	@Id
	@GeneratedValue
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@RoleName
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@RoleGroups
	@ManyToMany(targetEntity = YRole.class)
	@JoinTable(name = "RoleGroups", joinColumns = @JoinColumn(name = "RoleId"), inverseJoinColumns = @JoinColumn(name = "GroupId"))
	public Set<YRole> getGroups() {
		if (groups == null) {
			groups = new HashSet<YRole>();
		}
		return groups;
	}

	public void setGroups(Set<YRole> groups) {
		this.groups = groups;
	}
}
