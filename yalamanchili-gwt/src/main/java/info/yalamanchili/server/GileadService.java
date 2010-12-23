package info.yalamanchili.server;

import info.yalamanchili.commons.JNDIUtils;

import javax.persistence.EntityManagerFactory;

import net.sf.gilead.core.hibernate.jboss.HibernateJBossUtil;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import net.sf.gilead.gwt.PersistentRemoteService;

/* This is a super class all entity Async impls must extend to init Gilead hibernate*/
public abstract class GileadService extends PersistentRemoteService {

	private static final long serialVersionUID = 1L;

	public GileadService(String persistenceJNDIName) {
		GwtConfigurationHelper
				.initGwtStatelessBeanManager(new HibernateJBossUtil(
						getEntityManagerFactory(persistenceJNDIName)));

	}

	private EntityManagerFactory getEntityManagerFactory(
			String persistenceJNDIName) {
		return (EntityManagerFactory) JNDIUtils.lookup(persistenceJNDIName);
	}
}