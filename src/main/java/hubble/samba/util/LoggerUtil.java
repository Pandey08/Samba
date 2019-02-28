package hubble.samba.util;
import org.apache.log4j.Logger;


public class LoggerUtil {
	
	static Logger logger = Logger.getLogger("Logger");
	static Logger reportLogger = Logger.getLogger("reportLogger");

	
	public static void debug(Object message){
		logger.debug(message);
	}
	
	public static void info(Object message){
		logger.info(message);
	}
	
	public static void warn(Object message){
		logger.warn(message);
	}

	public static void error(Object message){
		logger.error(message);
	
	}
	
	public static void fatal(Object message){
		logger.fatal(message);

	}

	public static void printStackTrace(Throwable e){
		logger.error(e.getMessage(), e);
	}
	
	public static void consoleInfo(Object message){
		logger.info(message);
	}
	
}
