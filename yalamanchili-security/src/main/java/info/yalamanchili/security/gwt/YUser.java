package info.yalamanchili.security.gwt;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import net.sf.gilead.pojo.gwt.LightEntity;

import org.jboss.seam.annotations.security.management.UserPassword;
import org.jboss.seam.annotations.security.management.UserPrincipal;
import org.jboss.seam.annotations.security.management.UserRoles;

@Entity
public class YUser extends LightEntity {

	private static final long serialVersionUID = 4093061330460788496L;

	private Long userId;

	private String username;
	// TODO need to remove
	// @UserPassword(hash = "md5")
	private String passwordHash;

	private Set<YRole> roles;

	@Id
	@GeneratedValue
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@UserPrincipal
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@UserPassword(hash = "none")
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@UserRoles
	@ManyToMany(targetEntity = YRole.class)
	@JoinTable(name = "UserRoles", joinColumns = @JoinColumn(name = "UserId"), inverseJoinColumns = @JoinColumn(name = "RoleId"))
	public Set<YRole> getRoles() {
		if (roles == null) {
			roles = new HashSet<YRole>();
		}
		return roles;
	}

	public void setRoles(Set<YRole> roles) {
		this.roles = roles;
	}

	public void addRole(YRole role) {
		this.getRoles().add(role);
	}

}
