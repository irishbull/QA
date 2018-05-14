package ta.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ta.testcases.MyTest;
import ta.testcases.SignUpFormTest;

@RunWith(Suite.class)
@SuiteClasses({ SignUpFormTest.class, MyTest.class })

public class TestSuite {

}
