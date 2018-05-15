package ta.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ta.testcases.My_2_Test;
import ta.testcases.SignUpForm_2_Test;

@RunWith(Suite.class)
@SuiteClasses({SignUpForm_2_Test.class, My_2_Test.class})

public class TestSuite_2 {

}
