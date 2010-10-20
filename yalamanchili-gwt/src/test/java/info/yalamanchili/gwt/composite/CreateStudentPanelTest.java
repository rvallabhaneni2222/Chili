package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.CreateComposite.CreateCompositeType;
import info.yalamanchili.gwt.rpc.GWTServiceAsync;

import java.util.ArrayList;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.user.client.ui.Button;
import com.octo.gwt.test.AbstractGwtEasyMockTest;
import com.octo.gwt.test.Mock;
import com.octo.gwt.test.utils.GwtTestReflectionUtils;
import com.octo.gwt.test.utils.events.Browser;

public class CreateStudentPanelTest extends AbstractGwtEasyMockTest {
	@Mock
	private GWTServiceAsync service;
	private CreateStudentPanel panel;

	@Before
	public void init() throws Exception {
		panel = new CreateStudentPanel(CreateCompositeType.CREATE);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void checkCreate() {
		Button create = GwtTestReflectionUtils.getPrivateFieldValue(panel,
				"create");
//		service.validateField(EasyMock.eq("OCTO"), EasyMock.eq("OCTO1"),
//				EasyMock.isA(ALAsyncCallback.class));
		Object res = new ArrayList<String>();
		expectServiceAndCallbackOnSuccess(res);
		replay();
		Browser.click(create);
	}
}
