package Runner;

//import static io.cucumber.junit.platform.engine.Constants.*;
//import org.junit.platform.suite.api.ConfigurationParameter;
//import org.junit.platform.suite.api.SelectClasspathResource;
//import org.junit.platform.suite.api.Suite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@Suite
//@SelectClasspathResource("features")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value ="stepdefinitions")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@selenium")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = "stepdefinitions",
	    monochrome = true,
	    plugin = {"html:target/cucumber.html"}
	)
public class TestRunner extends AbstractTestNGCucumberTests{

}


