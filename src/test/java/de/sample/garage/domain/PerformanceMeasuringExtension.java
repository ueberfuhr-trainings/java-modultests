package de.sample.garage.domain;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Logger;

public class PerformanceMeasuringExtension implements BeforeEachCallback, AfterEachCallback {

    private static final Logger logger = Logger.getLogger(PerformanceMeasuringExtension.class.getName());
    private long startTime;

    @Override
    public void beforeEach(ExtensionContext context) {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        long endTime = System.currentTimeMillis();
        logger.info(
          "\n"
          +
          context.getRequiredTestClass().getSimpleName()
          + " - "
          + context.getDisplayName()
          + "\n"
          + (endTime-startTime)
          + "ms"
        );
    }

}
