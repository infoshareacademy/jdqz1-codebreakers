package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.LoginTests;
import tests.RegistrationTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTests.class,
        RegistrationTests.class
})

public class AllSeleniumTests {

}

