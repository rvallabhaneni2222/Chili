package info.yalamanchili.security;

import info.yalamanchili.security.jpa.YRole;
import info.yalamanchili.security.jpa.YUser;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.async.Asynchronous;

@Name("YSecurityStartup")
@Scope(ScopeType.APPLICATION)
@Startup
@Transactional
public class YSecurityStartup {

	@In(create = true)
	protected EntityManager yem;

	@Observer("org.jboss.seam.postInitialization")
	@Asynchronous
	// TODO fix transacton exceptiopn
	public void initUsersRoles() {
		// System.out.println("QQQQQQQQQ load security init");
		// YRole userRole = new YRole();
		// userRole.setRolename("user");
		// yem.merge(userRole);
		// YRole adminRole = new YRole();
		// adminRole.setRolename("admin");
		// yem.merge(adminRole);
		//
		// YUser user = new YUser();
		// user.setUsername("user");
		// user.setPasswordHash("user");
		// user.getRoles().add(userRole);
		// yem.merge(user);
		//
		// YUser admin = new YUser();
		// admin.setUsername("admin");
		// admin.setPasswordHash("admin");
		// admin.getRoles().add(userRole);
		// admin.getRoles().add(adminRole);
		// yem.merge(admin);
	}
}
