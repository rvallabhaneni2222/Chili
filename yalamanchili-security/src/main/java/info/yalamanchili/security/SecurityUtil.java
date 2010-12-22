package info.yalamanchili.security;

import info.yalamanchili.security.jpa.YRole;
import info.yalamanchili.security.jpa.YUser;

import javax.persistence.EntityManager;

public class SecurityUtil {

	public static YUser createUser(EntityManager em, String username,
			String password) {
		YUser user = new YUser();
		user.setUsername(username);
		user.setPasswordHash(password);
		em.persist(user);
		return user;
	}

	public static YRole createRole(EntityManager em, String rolename,
			String password) {
		YRole role = new YRole();
		role.setRolename(rolename);
		em.persist(role);
		return role;
	}

	public static YUser addRoleToUser(EntityManager em, YUser user, YRole role) {
		user.getRoles().add(role);
		return em.merge(user);
	}

	public static YRole addRolesToGroup(EntityManager em, YRole group,
			YRole... roles) {
		for (YRole role : roles) {
			group.getGroups().add(role);
		}
		return em.merge(group);
	}
}
