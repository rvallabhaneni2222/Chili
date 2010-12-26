package info.yalamanchili.gwt.security;

import info.yalamanchili.gwt.beans.MultiSelectObj;
import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.security.jpa.YRole;
import info.yalamanchili.security.jpa.YUser;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface AdminService extends RemoteService {
	/* USER ENTITY */
	public YUser createUser(YUser entity);

	public YUser readUser(Long id);

	public YUser updateUser(YUser entity);

	public void deleteUser(YUser entity);

	public TableObj<YUser> getTableObjUser(int start);

	public List<String> getSuggestionsForNameUser(String name, YUser entity);

	public List<YUser> getEntitiesUser(YUser entity);

	public Map<Long, String> getListBoxValues(String[] columns);

	public MultiSelectObj getUserRoles(YUser user, String[] columns);

	public List<YUser> searchUser(String searchText);

	/* ROLE ENTITY */
	public YRole createRole(YRole entity);

	public YRole readRole(Long id);

	public YRole updateRole(YRole entity);

	public void deleteRole(YRole entity);

	public TableObj<YRole> getTableObjRole(int start);

	public List<String> getSuggestionsForNameRole(String name, YRole entity);

	public List<YRole> getEntitiesRole(YRole entity);

	public Map<Long, String> getListBoxValuesRole(String[] columns);

	public List<YRole> searchRole(String searchText);

	public void addRoles(YUser user, List<Long> children);

	public List<YRole> getRolesForUser(Long user);

	public List<YRole> getRolesForRole(Long user);

	public static class AdminServiceAsync {

		/** The adminservice. */
		private static info.yalamanchili.gwt.security.AdminServiceAsync adminService;

		public static synchronized info.yalamanchili.gwt.security.AdminServiceAsync instance() {
			if (adminService == null) {
				adminService = (info.yalamanchili.gwt.security.AdminServiceAsync) GWT
						.create(AdminService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) adminService;
				String moduleRelativeURL = GWT.getModuleBaseURL()
						+ "seam/resource/gwt2";
				endpoint.setServiceEntryPoint(moduleRelativeURL);
			}
			return adminService;
		}
	}
}
