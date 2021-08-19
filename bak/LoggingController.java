package kbbg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Default Logging Service
 */
public class LoggingController {

  public static void logger(Class<?> clazz, String level, String message) {
    Logger log = LogManager.getLogger(clazz);
    switch (level) {
      case "warn":
        log.warn(message);
        break;
      case "info":
        log.info(message);
        break;
      case "debug":
        log.debug(message);
        break;
      case "trace":
        log.trace(message);
        break;
      case "error":
        log.error(message);
        break;
      default:
        log.info(message);
        break;
    }
  }
}
