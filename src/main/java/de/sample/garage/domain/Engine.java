package de.sample.garage.domain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An engine.
 */
public class Engine {

    private static final Logger logger = Logger.getLogger(Engine.class
        .getName());

    private final double hp;
    private final Thread engineThread;
    private boolean engineStarted;

    public Engine() {
        this.hp = 190;
        this.engineThread = new Thread() {
            @Override
            public void run() {
                while (engineStarted) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.log(
                            Level.WARNING,
                            this
                                + " is not working correctly at the moment! Stop the engine when this problem occurs again.");
                    }
                    logger.log(Level.INFO, this + " is currently running");
                }
            }
        };
    }

    /**
     * Returns the horse power.
     *
     * @return the horse power.
     */
    public double getHp() {
        return hp;
    }

    public boolean isEngineStarted() {
        return engineStarted;
    }

    /**
     * Starts the engine.
     */
    public void start() {
        engineThread.start();
        engineStarted = true;
    }

    /**
     * Stops the engine.
     */
    public void stop() {
        engineStarted = false;
    }

}
