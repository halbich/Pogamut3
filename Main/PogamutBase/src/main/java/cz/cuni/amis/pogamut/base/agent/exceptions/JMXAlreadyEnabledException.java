package cz.cuni.amis.pogamut.base.agent.exceptions;

import java.util.logging.Logger;

import cz.cuni.amis.utils.exception.PogamutJMXException;

@SuppressWarnings("serial")
public class JMXAlreadyEnabledException extends PogamutJMXException {
	
	/**
	 * Constructs a new exception with the specified detail message.
	 * <p><p>
	 * Not logging anything anywhere on its own.
	 * 
	 * @param message
	 * @param cause
	 */
	public JMXAlreadyEnabledException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructs a new exception with the specified detail message.
	 * <p><p>
	 * Not logging anything anywhere on its own.
	 * 
	 * @param message
	 * @param origin which object does produced the exception
	 */
	public JMXAlreadyEnabledException(String message, Object origin) {
		super(message, origin);
	}
	
	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * <p><p>
	 * Not logging anything anywhere on its own.
	 * 
	 * @param message
	 * @param cause
	 * @param origin which object does produced the exception
	 */
	public JMXAlreadyEnabledException(String message, Throwable cause, Object origin) {
		super(message, cause, origin);
	}

	
	/**
	 * Constructs a new exception with the specified detail message.
	 * <p><p>
	 * Logs the exception via specified Logger.
	 * 
	 * @param message
	 * @param log
	 * @param origin which object does produced the exception
	 */
	public JMXAlreadyEnabledException(String message, Logger log, Object origin){
		super(message, log, origin);
	}
	
	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * <p><p>
	 * Logs the exception via specified Logger.
	 * 
	 * @param message
	 * @param cause
	 * @param origin which object does produced the exception
	 */
	public JMXAlreadyEnabledException(String message, Throwable cause, Logger log, Object origin) {
		super(message, cause, log, origin);
	}

}
