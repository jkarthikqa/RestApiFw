package cocumber.Options;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepDefinations"},
        plugin = "json:target/jsonReports/cucumber-report.json",
        tags = "@AddPlace"
)
public class TestRunner {
}
