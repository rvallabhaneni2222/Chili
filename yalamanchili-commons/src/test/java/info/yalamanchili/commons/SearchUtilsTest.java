package info.yalamanchili.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchUtilsTest {
	private static final Log log = LogFactory.getLog(SearchUtilsTest.class);

	@Before
	public void init() {

	}

	@Test
	public void testSplitSearchString() {
		SearchUtils searchUtils = new SearchUtils();
		System.out.println(searchUtils
				.splitSearchString("desi ggh 7 guys", ' '));
	}

	@After
	public void destroy() {

	}
}
