package info.yalamanchili.security;

import info.yalamanchili.commons.ReflectionUtils;
import info.yalamanchili.commons.SearchUtils;
import info.yalamanchili.gwt.beans.MultiSelectObj;
import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.security.gwt.AdminService;
import info.yalamanchili.security.gwt.YRole;
import info.yalamanchili.security.gwt.YUser;
import info.yalamanchili.server.GWTServletUtils;
import info.yalamanchili.server.GileadService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.hibernate.search.FullTextQuery;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.remoting.WebRemote;

@Transactional
@Scope(ScopeType.SESSION)
@Name("info.yalamanchili.security.gwt.AdminService")
public class AdminServiceImpl extends GileadService implements AdminService {

	public AdminServiceImpl() {
		super("java:/yalamanchili");
	}

	@In(create = true)
	protected EntityManager yem;

	/* security YUSER */

	@Override
	@WebRemote
	public YUser createUser(YUser entity) {
		return (YUser) getBeanManager().clone(yem.merge(entity));
	}

	@Override
	@WebRemote
	public YUser readUser(Long id) {
		return (YUser) getBeanManager().clone(yem.find(YUser.class, id));
	}

	@Override
	@WebRemote
	public YUser updateUser(YUser entity) {
		return (YUser) getBeanManager().merge(yem.merge(entity));
	}

	@Override
	@WebRemote
	public void deleteUser(YUser entity) {
		yem.remove(yem.find(YUser.class, entity.getUserId()));
	}

	@Override
	@WebRemote
	public TableObj<YUser> getTableObjUser(int start) {
		List<YUser> users = new ArrayList<YUser>();
		TableObj<YUser> tableObj = new TableObj<YUser>();
		TypedQuery<YUser> getUsers = yem.createQuery(
				"from " + YUser.class.getCanonicalName(), YUser.class);
		getUsers.setFirstResult(start);
		getUsers.setMaxResults(10);
		for (YUser u : getUsers.getResultList()) {
			users.add((YUser) getBeanManager().clone(u));
		}
		tableObj.setRecords(users);
		tableObj.setNumberOfRecords(AdminServiceImpl.getEntitySize(yem,
				YUser.class));
		return tableObj;
	}

	@Override
	@WebRemote
	public List<String> getSuggestionsForNameUser(String name, YUser entity) {
		Query query = yem.createQuery(GWTServletUtils
				.getSuggestionsQueryForName(name, new YUser()));
		return query.getResultList();
	}

	@Override
	@WebRemote
	public List<YUser> getEntitiesUser(YUser entity) {
		List<YUser> entities = new ArrayList<YUser>();
		Query getEntities = yem.createQuery(GWTServletUtils
				.getSearchQueryString(entity));
		for (Object obj : getEntities.getResultList()) {
			entities.add((YUser) getBeanManager().clone(obj));
		}
		return entities;
	}

	@Override
	@WebRemote
	public Map<Long, String> getListBoxValues(String[] columns) {
		String query = GWTServletUtils.getListBoxResultsQueryString(
				YUser.class.getCanonicalName(), columns);
		Map<Long, String> values = new HashMap<Long, String>();
		Query getListBoxValues = yem.createQuery(query);
		for (Object obj : getListBoxValues.getResultList()) {
			Object[] obs = (Object[]) obj;
			values.put((Long) obs[0], (String) obs[1]);
		}
		return values;
	}

	@Override
	@WebRemote
	public MultiSelectObj<YRole> getUserRoles(YUser user, String[] columns) {
		user = yem.find(YUser.class, user.getUserId());
		Set<Long> ids = new HashSet<Long>();
		List<YRole> childObjs = new ArrayList<YRole>();
		MultiSelectObj<YRole> obj = new MultiSelectObj<YRole>();
		obj.setAvailable(getListBoxValuesRole(columns));
		for (YRole role : user.getRoles()) {
			ids.add(role.getRoleId());
			childObjs.add((YRole) getBeanManager().clone(role));
		}
		obj.setSelected(ids);
		obj.setSelectedObjs(childObjs);
		return obj;
	}

	@Override
	@WebRemote
	public List<YUser> searchUser(String searchText) {
		List<YUser> results = new ArrayList<YUser>();
		org.apache.lucene.search.Query luceneQuery = SearchUtils
				.getLuceneQuery(searchText, "id", new StandardAnalyzer(
						Version.LUCENE_30), ReflectionUtils.getBeanProperties(
						YUser.class, info.yalamanchili.commons.DataType.STRING));
		FullTextQuery query = SearchUtils.getFullTextSession(yem)
				.createFullTextQuery(luceneQuery, YUser.class);
		for (Object obj : query.list()) {
			results.add((YUser) getBeanManager().clone((obj)));
		}
		return results;
	}

	public static <T extends Serializable> Long getEntitySize(EntityManager em,
			Class<?> clazz) {
		String query = "select count(entity) from " + clazz.getCanonicalName()
				+ " entity";
		Query getEntitiesSize = em.createQuery(query);
		return (Long) getEntitiesSize.getSingleResult();
	}

	/* security YROLE */

	@Override
	@WebRemote
	public YRole createRole(YRole entity) {
		return (YRole) getBeanManager().clone(yem.merge(entity));
	}

	@Override
	@WebRemote
	public YRole readRole(Long id) {
		return (YRole) getBeanManager().clone(yem.find(YRole.class, id));
	}

	@Override
	@WebRemote
	public YRole updateRole(YRole entity) {
		return (YRole) getBeanManager().merge(yem.merge(entity));
	}

	@Override
	@WebRemote
	public void deleteRole(YRole entity) {
		yem.remove(yem.find(YUser.class, entity.getRoleId()));
	}

	@Override
	@WebRemote
	public TableObj<YRole> getTableObjRole(int start) {
		List<YRole> roles = new ArrayList<YRole>();
		TableObj<YRole> tableObj = new TableObj<YRole>();
		TypedQuery<YRole> getUsers = yem.createQuery(
				"from " + YRole.class.getCanonicalName(), YRole.class);
		getUsers.setFirstResult(start);
		getUsers.setMaxResults(10);
		for (YRole u : getUsers.getResultList()) {
			roles.add((YRole) getBeanManager().clone(u));
		}
		tableObj.setRecords(roles);
		tableObj.setNumberOfRecords(AdminServiceImpl.getEntitySize(yem,
				YRole.class));
		return tableObj;
	}

	@Override
	@WebRemote
	public List<String> getSuggestionsForNameRole(String name, YRole entity) {
		Query query = yem.createQuery(GWTServletUtils
				.getSuggestionsQueryForName(name, new YRole()));
		return query.getResultList();
	}

	@Override
	@WebRemote
	public List<YRole> getEntitiesRole(YRole entity) {
		List<YRole> entities = new ArrayList<YRole>();
		Query getEntities = yem.createQuery(GWTServletUtils
				.getSearchQueryString(entity));
		for (Object obj : getEntities.getResultList()) {
			entities.add((YRole) getBeanManager().clone(obj));
		}
		return entities;
	}

	@Override
	@WebRemote
	public Map<Long, String> getListBoxValuesRole(String[] columns) {
		String query = GWTServletUtils.getListBoxResultsQueryString(
				YRole.class.getCanonicalName(), columns);
		Map<Long, String> values = new HashMap<Long, String>();
		Query getListBoxValues = yem.createQuery(query);
		for (Object obj : getListBoxValues.getResultList()) {
			Object[] obs = (Object[]) obj;
			values.put((Long) obs[0], (String) obs[1]);
		}
		return values;
	}

	@Override
	@WebRemote
	public List<YRole> searchRole(String searchText) {
		List<YRole> results = new ArrayList<YRole>();
		org.apache.lucene.search.Query luceneQuery = SearchUtils
				.getLuceneQuery(searchText, "id", new StandardAnalyzer(
						Version.LUCENE_30), ReflectionUtils.getBeanProperties(
						YRole.class, info.yalamanchili.commons.DataType.STRING));
		FullTextQuery query = SearchUtils.getFullTextSession(yem)
				.createFullTextQuery(luceneQuery, YRole.class);
		for (Object obj : query.list()) {
			results.add((YRole) getBeanManager().clone((obj)));
		}
		return results;
	}

	@Override
	@WebRemote
	public void addRoles(YUser user, List<Long> ids) {
		user = yem.find(YUser.class, user.getUserId());
		for (Long id : ids) {
			YRole child = (YRole) yem.find(YRole.class, id);
			if (child == null) {
				throw new RuntimeException("error find entity with id:" + id
						+ ":on:" + child.getClass().getName());
			}
			user.addRole(child);
		}
	}

	@Override
	@WebRemote
	public List<YRole> getRolesForUser(Long id) {
		List<YRole> res = new ArrayList<YRole>();
		for (YRole obj : yem.find(YUser.class, id).getRoles()) {
			res.add((YRole) getBeanManager().clone(obj));
		}
		return res;
	}

	@Override
	@WebRemote
	public List<YRole> getRolesForRole(Long id) {
		List<YRole> res = new ArrayList<YRole>();
		for (YRole obj : yem.find(YRole.class, id).getGroups()) {
			res.add((YRole) getBeanManager().clone(obj));
		}
		return res;
	}

	@Override
	@WebRemote
	public MultiSelectObj<YRole> getRoleRoles(YRole parent, String[] columns) {
		parent = yem.find(YRole.class, parent.getRoleId());
		Set<Long> childIds = new HashSet<Long>();
		List<YRole> childObjs = new ArrayList<YRole>();
		MultiSelectObj<YRole> obj = new MultiSelectObj<YRole>();
		obj.setAvailable(getListBoxValuesRole(columns));
		for (YRole entity : parent.getGroups()) {
			childObjs.add((YRole) getBeanManager().clone(entity));
			childIds.add(entity.getRoleId());
		}
		obj.setSelectedObjs(childObjs);
		obj.setSelected(childIds);
		return obj;
	}

	@Override
	@WebRemote
	public void addRoles(YRole role, List<Long> children) {
		role = yem.find(YRole.class, role.getRoleId());
		for (Long id : children) {
			YRole child = (YRole) yem.find(YRole.class, id);
			if (child == null) {
				throw new RuntimeException("error find entity with id:" + id
						+ ":on:" + child.getClass().getName());
			}
			role.getGroups().add(child);
		}

	}

	@Override
	@WebRemote
	public void removeRoles(YUser user, List<Long> children) {
		user = yem.find(YUser.class, user.getUserId());
		for (Long id : children) {
			YRole child = (YRole) yem.find(YRole.class, id);
			if (child == null) {
				throw new RuntimeException("error find entity with id:" + id
						+ ":on:" + child.getClass().getName());
			}
			user.getRoles().remove(child);
		}

	}

	@Override
	@WebRemote
	public void removeRoles(YRole role, List<Long> children) {
		role = yem.find(YRole.class, role.getRoleId());
		for (Long id : children) {
			YRole child = (YRole) yem.find(YRole.class, id);
			if (child == null) {
				throw new RuntimeException("error find entity with id:" + id
						+ ":on:" + child.getClass().getName());
			}
			role.getGroups().remove(child);
		}
	}

}
