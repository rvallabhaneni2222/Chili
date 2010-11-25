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
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Name("StartUP")
@Scope(ScopeType.APPLICATION)
@Startup
@BypassInterceptors
public class StartUp {

	@In(create = true)
	protected EntityManager yem;

	@Observer("org.jboss.seam.postInitialization")
	@Asynchronous
	public void initUsersRoles() {
		//TODO fix null entitymanager issue
//		log.debug("in init user roles");
//		YRole userRole = new YRole();
//		userRole.setRolename("user");
//		yem.merge(userRole);
//		YRole adminRole = new YRole();
//		adminRole.setRolename("admin");
//		yem.merge(adminRole);
//
//		YUser user = new YUser();
//		user.setUsername("user");
//		user.setPasswordHash("user");
//		user.getRoles().add(userRole);
//		yem.merge(user);
//
//		YUser admin = new YUser();
//		admin.setUsername("admin");
//		admin.setPasswordHash("admin");
//		admin.getRoles().add(userRole);
//		admin.getRoles().add(adminRole);
//		yem.merge(admin);
	}
}
