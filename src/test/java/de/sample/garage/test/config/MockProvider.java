package de.sample.garage.test.config;

@FunctionalInterface
public interface MockProvider {

    <T> T mock(Class<T> mockClass);

}
