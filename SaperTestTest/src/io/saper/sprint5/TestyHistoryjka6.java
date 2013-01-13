package io.saper.sprint5;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestyHistoryjka6
{

	public static Test suite()
	{
		TestSuite suite = new TestSuite(TestyHistoryjka6.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(testCase111.class);
		suite.addTestSuite(testCase112.class);
		suite.addTestSuite(testCase113.class);
		//$JUnit-END$
		return suite;
	}

}
