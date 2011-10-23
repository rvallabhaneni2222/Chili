package info.yalamanchili.gwt.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.web.bindery.event.shared.UmbrellaException;

//TODO make it generic to handle all client side exception
public class GWTUncaughtExceptionHandler implements UncaughtExceptionHandler {
	private Logger logger = Logger.getLogger(GWTUncaughtExceptionHandler.class
			.getName());

	@Override
	public void onUncaughtException(Throwable e) {
		// Get rid of UmbrellaException
		Throwable exceptionToDisplay = getExceptionToDisplay(e);
		// Replace with your favorite message dialog, e.g. GXT's MessageBox
		logger.log(Level.SEVERE, exceptionToDisplay.getMessage());
		String text = "Uncaught exception: ";
		while (e != null) {
			StackTraceElement[] stackTraceElements = e.getStackTrace();
			text += e.toString() + "\n";
			for (int i = 0; i < stackTraceElements.length; i++) {
				text += "    at " + stackTraceElements[i] + "\n";
			}
			e = e.getCause();
			if (e != null) {
				text += "Caused by: ";
			}
		}
		logger.log(Level.SEVERE, text);
	}

	private static Throwable getExceptionToDisplay(Throwable throwable) {
		Throwable result = throwable;
		if (throwable instanceof UmbrellaException
				&& ((UmbrellaException) throwable).getCauses().size() == 1) {
			result = ((UmbrellaException) throwable).getCauses().iterator()
					.next();
		}
		return result;
	}
}