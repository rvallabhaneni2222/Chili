package info.yalamanchili.security;

import info.yalamanchili.security.gwt.YRole;
import info.yalamanchili.security.gwt.YUser;

import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.util.Base64;

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

	public static YUser getUserForPrincipal(EntityManager em,
			Principal principal) {
		Query getUserQuery = em.createQuery("from "
				+ YUser.class.getSimpleName() + " where username='"
				+ principal.getName() + "'");
		return (YUser) getUserQuery.getSingleResult();
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

	public static String getMD5Hash(String input) {
		try {
			byte[] hash = java.security.MessageDigest.getInstance("MD5")
					.digest(input.getBytes());
			return Base64.encodeBytes(hash);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
