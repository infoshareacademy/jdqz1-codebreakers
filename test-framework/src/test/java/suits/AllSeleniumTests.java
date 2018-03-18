package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.LoginTests;
import tests.RegistrationIncorrectConfirmPassword;
import tests.RegistrationPasswordIsTooShort;
import tests.RegistrationTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTests.class,
        RegistrationIncorrectConfirmPassword.class,
        RegistrationPasswordIsTooShort.class,
        RegistrationTests.class
})

public class AllSeleniumTests {

}

