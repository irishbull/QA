package com.lm.ta.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.lm.ta.testcases.TestCase_001;
import com.lm.ta.testcases.TestCase_002;
import com.lm.ta.testcases.TestCase_003;

@RunWith(Suite.class)
@SuiteClasses({ TestCase_001.class,
	            TestCase_002.class, 
		        TestCase_003.class })

public class TestSuite {

}
