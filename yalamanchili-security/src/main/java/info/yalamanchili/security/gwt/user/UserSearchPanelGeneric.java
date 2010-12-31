package info.yalamanchili.security.gwt.user;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.SearchPanelCompositeGeneric;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.widgets.ALSuggestBox;
import info.yalamanchili.security.gwt.AdminService.AdminServiceAsync;
import info.yalamanchili.security.gwt.SecurityWelcome;
import info.yalamanchili.security.gwt.YUser;
import info.yalamanchili.security.gwt.user.ReadAllUsersPanel.YUserTableType;

import java.util.List;

public class UserSearchPanelGeneric extends SearchPanelCompositeGeneric<YUser> {

	private static UserSearchPanelGeneric instance;

	public static UserSearchPanelGeneric instance() {
		return instance;
	}

	public UserSearchPanelGeneric() {
		instance = this;
		initSearchPanelCompositeGeneric("YUser", null);
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgetsBeforeCaptionPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgets() {
		addField("username", false, true, DataType.STRING_FIELD);
		AdminServiceAsync.instance().getSuggestionsForNameUser("username",
				entity, new ALAsyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> values) {
						addSuggestBox("username", values);

					}

				});
	}

	@Override
	protected void configure() {

	}

	@Override
	protected void searchButtonClicked() {
		SecurityWelcome.entityPanel.clear();
		// TODO
		SecurityWelcome.entityPanel.add((new ReadAllUsersPanel(
				YUserTableType.READALL_SEARCH)));

	}

	@Override
	protected YUser populateEntity() {
		YUser user = new YUser();
		user.setUsername(((ALSuggestBox) fields.get("username")).getText());
		return user;
	}

	protected void postValidate() {

	}
}
