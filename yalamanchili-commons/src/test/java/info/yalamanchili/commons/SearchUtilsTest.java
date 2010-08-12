package info.yalamanchili.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchUtilsTest {
	private static final Log log = LogFactory.getLog(SearchUtilsTest.class);

	@Before
	public void init() {

	}

	@Test
	public void testSearch() {
		SearchUtils.getLuceneQuery("bob", "amtrim", new StandardAnalyzer(),
				"amtrim");
	}

	@After
	public void destroy() {

	}
}
