package de.sample.garage.domain;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SuiteDisplayName("Cucumber Tests")
@SelectPackages({"de.sample.garage.domain", "features.domain"})
@SelectClasspathResource("features.domain") // JDK 1.8
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "de.sample.garage.domain")
public class CucumberDomainTests {
}
