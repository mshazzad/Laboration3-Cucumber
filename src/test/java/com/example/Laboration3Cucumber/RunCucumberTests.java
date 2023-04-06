package com.example.Laboration3Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/example/Laboration3Cucumber/resources", plugin = {"pretty", "html:target/cucumber-reports","json:target/cucumber.json", "junit:target/cucumber.xml"})

public class RunCucumberTests {

}
