package fr.emse.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MoneyTest.class,
    MoneyBagTest.class
})
public class AllTests {
    // Empty class just to run all tests
}
