package info.yalamanchili.security.gwt;

import info.yalamanchili.gwt.beans.MultiSelectObj;
import info.yalamanchili.gwt.beans.TableObj;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminServiceAsync {
	/* FOR APP LOGIN */
	public void createUser(YUser entity, Set<String> roles,
			AsyncCallback<Void> response);

	public void login(String userName, String password,
			AsyncCallback<YUser> response);

	public void logout(AsyncCallback<Void> response);

	public void getUser(AsyncCallback<YUser> response);

	/* USER ENTITY */
	public void createUser(YUser entity, AsyncCallback<YUser> response);

	public void readUser(Long id, AsyncCallback<YUser> response);

	public void updateUser(YUser entity, AsyncCallback<YUser> response);

	public void deleteUser(YUser entity, AsyncCallback<java.lang.Void> response);

	public void getTableObjUser(int start,
			AsyncCallback<TableObj<YUser>> response);

	public void getSuggestionsForNameUser(String name, YUser entity,
			AsyncCallback<List<String>> response);

	public void getEntitiesUser(YUser entity,
			AsyncCallback<List<YUser>> response);

	public void getListBoxValues(String[] columns,
			AsyncCallback<Map<Long, String>> response);

	public void getUserRoles(YUser user, String[] columns,
			AsyncCallback<MultiSelectObj<YRole>> response);

	public void searchUser(String searchText,
			AsyncCallback<List<YUser>> response);

	/* ROLE ENTITY */
	public void createRole(YRole entity, AsyncCallback<YRole> response);

	public void readRole(Long id, AsyncCallback<YRole> response);

	public void updateRole(YRole entity, AsyncCallback<YRole> response);

	public void deleteRole(YRole entity, AsyncCallback<java.lang.Void> response);

	public void getTableObjRole(int start,
			AsyncCallback<TableObj<YRole>> response);

	public void getSuggestionsForNameRole(String name, YRole entity,
			AsyncCallback<List<String>> response);

	public void getEntitiesRole(YRole entity,
			AsyncCallback<List<YRole>> response);

	public void getListBoxValuesRole(String[] columns,
			AsyncCallback<Map<Long, String>> response);

	public void searchRole(String searchText,
			AsyncCallback<List<YRole>> response);

	public void addRoles(YUser user, List<Long> children,
			AsyncCallback<java.lang.Void> response);

	public void addRoles(YRole role, List<Long> children,
			AsyncCallback<java.lang.Void> response);

	public void removeRoles(YUser user, List<Long> children,
			AsyncCallback<java.lang.Void> response);

	public void removeRoles(YRole role, List<Long> children,
			AsyncCallback<java.lang.Void> response);

	public void getRolesForUser(Long user, AsyncCallback<List<YRole>> response);

	public void getRolesForRole(Long user, AsyncCallback<List<YRole>> response);

	public void getRoleRoles(YRole role, String[] columns,
			AsyncCallback<MultiSelectObj<YRole>> response);
}
