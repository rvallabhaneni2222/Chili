package info.yalamanchili.security;

import info.yalamanchili.security.jpa.YRole;
import info.yalamanchili.security.jpa.YUser;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;

@Name("info.yalamanchili.security.Startup")
@Scope(ScopeType.APPLICATION)
public class YSecurityStartup {
	@Logger
	static Log log;

	@In(create = true)
	protected EntityManager yem;

	@Observer("org.jboss.seam.postInitialization")
	@Transactional
	// TODO fix transacton exceptiopn
	public void initUsersRoles() {
		log.info("Initilizing security...");
		// USER ROLE
		YRole userRole = new YRole();
		userRole.setRolename("user");
		yem.persist(userRole);
		// ADMIN ROLE
		YRole adminRole = new YRole();
		adminRole.setRolename("admin");
		adminRole.getGroups().add(adminRole);
		adminRole.getGroups().add(userRole);
		yem.persist(adminRole);
		// USER
		YUser user = new YUser();
		user.setUsername("user");
		user.setPasswordHash("user");
		user.getRoles().add(userRole);
		yem.persist(user);
		// ADMIN
		YUser admin = new YUser();
		admin.setUsername("admin");
		admin.setPasswordHash("admin");
		admin.getRoles().add(adminRole);
		yem.persist(admin);
		log.info("Completed Security init");
	}
}
