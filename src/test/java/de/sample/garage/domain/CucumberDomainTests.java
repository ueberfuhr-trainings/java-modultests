package de.sample.garage.domain;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.OBJECT_FACTORY_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SuiteDisplayName("Cucumber Tests")
@SelectPackages({"de.sample.garage.domain", "features.domain"})
@SelectClasspathResource("features.domain") // JDK 1.8
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "de.sample.garage.domain")
// necessary when we use cucumber-spring -> select object factory without spring context
@ConfigurationParameter(key = OBJECT_FACTORY_PROPERTY_NAME, value = "io.cucumber.core.backend.DefaultObjectFactory")
public class CucumberDomainTests {
}
