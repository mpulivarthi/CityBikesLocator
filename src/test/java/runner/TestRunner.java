package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(features = {".//src//test//resources//features"}
        ,glue= {"steps"}
        ,plugin = { "html:target/cucumber-reports" }
        , monochrome = true
        )
@RunWith(Cucumber.class)
public class TestRunner {

}
