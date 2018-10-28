package mainpackage.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CategoriesControllerTest.class,
        CustomersControllerTest.class,
        ParamsControllerTest.class
})

public class ControllersSuite {
}
