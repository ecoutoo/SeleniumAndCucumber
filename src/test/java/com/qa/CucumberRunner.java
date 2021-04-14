package com.qa;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
features = "src/test/resources/cuke",
monochrome = true)
public class CucumberRunner {

}
