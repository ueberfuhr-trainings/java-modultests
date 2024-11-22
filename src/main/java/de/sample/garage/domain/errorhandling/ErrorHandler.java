package de.sample.garage.domain.errorhandling;

import javax.swing.*;

/**
 * A simple error handler that displays the exception within the UI.
 */
public class ErrorHandler {

    public void handleError(Throwable e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
