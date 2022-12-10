package de.sample.garage.test.config;

public interface MockProvider {

    <T> T mock(Class<T> mockClass);

}
