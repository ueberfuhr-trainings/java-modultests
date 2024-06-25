package de.sample.garage.domain.spring;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SuiteDisplayName("Cucumber Tests")
@SelectPackages({"de.sample.garage.domain.spring", "features.domain"})
@SelectClasspathResource("features.domain") // JDK 1.8
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "de.sample.garage.domain.spring")
public class CucumberSpringDomainTests {
}
