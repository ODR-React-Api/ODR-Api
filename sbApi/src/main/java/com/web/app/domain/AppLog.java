package com.web.app.domain;

import org.springframework.stereotype.Component;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class AppLog {
  private static final Logger logger = LogManager.getLogger(AppLog.class);

  public static void debug(String msg) {
    logger.debug(msg);
  }

  public static void info(String msg) {
    logger.info(msg);
  }

  public static void warn(String msg) {
    logger.warn(msg);
  }

  public static void error(String msg) {
    logger.error(msg);
  }

  public static void fatal(String msg) {
    logger.fatal(msg);
  }

  // logger.fatal("--------- Log测试 --------");
  // logger.debug("This is a debug message.");
  // logger.info("This is an info message.");
  // logger.warn("This is a warning message.");
  // logger.error("This is an error message.");
  // logger.fatal("This is a fatal error message.");
}
