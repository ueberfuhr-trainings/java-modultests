package de.ars.schulung.tests.garage.errorhandling;

import javax.swing.JOptionPane;

/**
 * A simple error handler that displays the exception within the UI.
 */
public class ErrorHandler {

	public static void handleError(Throwable e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE);
	}

}
