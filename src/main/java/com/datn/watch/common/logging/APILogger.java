package com.datn.watch.common.logging;

import com.datn.watch.common.utils.AuthUtils;
import com.datn.watch.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @author tobi
 */
@Log4j2
public class APILogger {

    private final Logger logger;

    private static final String MESSAGE_TEMPLATE_2_PLACES = "{} {}";

    private static final String MESSAGE_TEMPLATE_FOR_API_INFO_REQ = "{} , {} , response : {}";
    private static final String MESSAGE_TEMPLATE_FOR_API_INFO_REQ_V2 = "\n{} {} \nresponse\n{}";
    private static final String MESSAGE_TEMPLATE_FOR_API_INFO_REQ_RES = "{} : {} , userInfo {} ,request {} , response {}";

    private static final String MESSAGE_TEMPLATE_FOR_API_INFO_REQ_RES_V2 = "\n{} : {} \nrequest\n{}, \nresponse\n{}";

    private static final String MESSAGE_TEMPLATE_FOR_EXCEPTION_WITH_TRACE = "{}\r\n{}";

    private static final boolean ENABLE_DEBUG = true;

    private static final boolean ENABLE_TRACE = true;

    public APILogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    /**
     * Log trace by format: yyyy-MM-dd hh:mm:ss [thread-name] TRACE packageName.ClassName at line
     * [line-number]: Message
     *
     * @param message message
     */
    public void logTrace(String message) {
        log(LogLevel.TRACE, getLineInfo() + ": " + message);
    }

    /**
     * Log trace by format: yyyy-MM-dd hh:mm:ss [thread-name] TRACE packageName.ClassName at line
     * [line-number]: Message {Param Object info}
     *
     * @param message message
     * @param params  params
     */
    public void logTrace(String message, Object... params) {
        log(LogLevel.TRACE, getLineInfo() + ": " + MESSAGE_TEMPLATE_2_PLACES, message, params);
    }

    /**
     * Log debug by format: yyyy-MM-dd hh:mm:ss [thread-name] DEBUG packageName.ClassName at line
     * [line-number]: Message
     *
     * @param message message
     */
    public void logDebug(String message) {
        log(LogLevel.DEBUG, getLineInfo() + ": " + message);
    }

    /**
     * Log debug by format: yyyy-MM-dd hh:mm:ss [thread-name] DEBUG packageName.ClassName at line
     * [line-number]: Message {Param Object info}
     *
     * @param message message
     * @param params  params
     */
    public void logDebug(String message, Object... params) {
        log(LogLevel.DEBUG, getLineInfo() + ": " + MESSAGE_TEMPLATE_2_PLACES, message, params);
    }

    /**
     * Log info by format: yyyy-MM-dd hh:mm:ss [thread-name] INFO packageName.ClassName: Message
     *
     * @param message message
     */
    public void logInfo(String message) {
        log(LogLevel.INFO, getLineInfo() + ": " + message);
    }

    /**
     * Log info by format: yyyy-MM-dd hh:mm:ss [thread-name] INFO packageName.ClassName: Message
     * {Param Object info}
     *
     * @param message message
     * @param params  params
     */
    public void logInfo(String message, Object... params) {
        log(LogLevel.INFO, getLineInfo() + ": " + MESSAGE_TEMPLATE_2_PLACES, message, params);
    }

    /**
     * Log warn by format: yyyy-MM-dd hh:mm:ss [thread-name] WARN packageName.ClassName: Message
     *
     * @param message message
     */
    public void logWarn(String message) {
        log(LogLevel.WARN, getLineInfo() + ": " + message);
    }

    /**
     * Log warn by format: yyyy-MM-dd hh:mm:ss [thread-name] WARN packageName.ClassName: Message
     * {Param Object info}
     *
     * @param message message
     * @param params  params
     */
    public void logWarn(String message, Object... params) {
        log(LogLevel.WARN, getLineInfo() + ": " + MESSAGE_TEMPLATE_2_PLACES, message, params);
    }

    /**
     * Log error by format: yyyy-MM-dd hh:mm:ss [thread-name] ERROR packageName.ClassName: Message
     *
     * @param message message
     */
    public void logError(String message) {
        log(LogLevel.ERROR, getLineInfo() + ": " + message);
    }

    /**
     * Log error by format: yyyy-MM-dd hh:mm:ss [thread-name] ERROR packageName.ClassName: Message
     * {Param Object info}
     *
     * @param message message
     * @param params  params
     */
    public void logError(String message, Object... params) {
        log(LogLevel.ERROR, getLineInfo() + ": " + MESSAGE_TEMPLATE_2_PLACES, message, params);
    }

    /**
     * Log fatal by format: yyyy-MM-dd hh:mm:ss [thread-name] ERROR packageName.ClassName: Message
     *
     * @param message message
     */
    public void logFatal(String message) {
        log(LogLevel.ERROR, "FATAL ERROR: " + getLineInfo() + ": " + message);
    }

    /**
     * Log fatal by format: yyyy-MM-dd hh:mm:ss [thread-name] FATAL packageName.ClassName: Message
     * {Param Object info}
     *
     * @param message message
     * @param params  params
     */
    public void logFatal(String message, Object... params) {
        log(LogLevel.ERROR, "FATAL ERROR: " + getLineInfo() + ": " + MESSAGE_TEMPLATE_2_PLACES, message,
                params);
    }

    /**
     * Log api info without param by format: yyyy-MM-dd hh:mm:ss [thread-name] TRACE
     * packageName.ClassName: "requestMethod apiName message"
     *
     * @param method method
     * @param api    api
     */
    public void logApi(RequestMethod method, String api) {
        log(LogLevel.INFO, MESSAGE_TEMPLATE_2_PLACES, method, api);
    }

    /**
     * Log api info without param by format: yyyy-MM-dd hh:mm:ss [thread-name] TRACE
     * packageName.ClassName: "requestMethod apiName message"
     *
     * @param method   method
     * @param api      api
     * @param response response
     */
    public void logApi(RequestMethod method, String api, Object response) {
        try {
            log(LogLevel.DEBUG, MESSAGE_TEMPLATE_FOR_API_INFO_REQ, method, api,
                    StringUtils.getJSON(response));
        } catch (Exception e) {
            log(LogLevel.DEBUG, MESSAGE_TEMPLATE_FOR_API_INFO_REQ, method, api, response);
        }
    }

    /**
     * Log api info with params by format: yyyy-MM-dd hh:mm:ss [thread-name] TRACE
     * packageName.ClassName: "requestMethod apiName message objectPramList(json)"
     *
     * @param method   method
     * @param api      api
     * @param request  request
     * @param response response
     */
    public void logApi(RequestMethod method, String api, Object request, Object response) {
        try {
            log(LogLevel.DEBUG, MESSAGE_TEMPLATE_FOR_API_INFO_REQ_RES, method, api, AuthUtils.getUserId(),
                    StringUtils.getJSON(request), StringUtils.getJSON(response));
        } catch (Exception e) {
            log(LogLevel.DEBUG, MESSAGE_TEMPLATE_FOR_API_INFO_REQ_RES, method, api, request, response);
        }
    }

    /**
     * Log exception by format: yyyy-MM-dd hh:mm:ss [thread-name] ERROR packageName.ClassName: Message
     * stackTrace
     *
     * @param message   message
     * @param throwable throwable
     */
    public void logException(String message, Throwable throwable) {
        try {
            logError(MESSAGE_TEMPLATE_FOR_EXCEPTION_WITH_TRACE, message, getStackTrace(throwable));
        } catch (Exception e) {
            logError(getStackTrace(e));
        }
    }

    /**
     * Log exception without message yyyy-MM-dd hh:mm:ss [thread-name] ERROR packageName.ClassName:
     * stackTrace
     *
     * @param throwable throwable
     */
    public void logException(Throwable throwable) {
        try {
            logException(throwable.getMessage(), throwable);
        } catch (Exception e) {
            logError(getStackTrace(e));
        }
    }

    /**
     * Log by level with param
     *
     * @param level  level
     * @param format format
     * @param params params
     */
    public void log(LogLevel level, String format, Object... params) {
        try {
            switch (level) {
                case TRACE -> {
                    if (ENABLE_TRACE) {
                        logger.trace(format, params);
                    }
//                    log.trace(format, params);
                }
                case DEBUG -> {
                    if (ENABLE_DEBUG) {
                        logger.debug(format, params);
                    }
//                    log.debug(format, params);
                }
                case INFO -> {
                    logger.info(format, params);
                    log.info(format, params);
                }
                case WARN -> {
                    logger.warn(format, params);
                    log.warn(format, params);
                }
                case ERROR -> {
                    logger.error(format, params);
                    log.error(format, params);
                }
                case FATAL -> {
                    logger.error("FATAL ERROR: " + format, params);
                    log.error("FATAL ERROR: " + format, params);
                }
                default -> {
                    logger.info(format, params);
                    log.info(format, params);
                }
            }
        } catch (Exception e) {
            logError(e.getMessage());
        }
    }

    /**
     * Log by level without params
     *
     * @param level   level
     * @param message message
     */
    public void log(LogLevel level, String message) {
        try {
            switch (level) {
                case TRACE -> {
                    if (ENABLE_TRACE) {
                        logger.trace(message);
//                        log.trace(message);
                    }
                }
                case DEBUG -> {
                    if (ENABLE_DEBUG) {
                        logger.debug(message);
//                        log.debug(message);
                    }
                }
                case WARN -> {
                    logger.warn(message);
//                    log.warn(message);
                }
                case ERROR -> {
                    logger.error(message);
//                    log.error(message);
                }
                case FATAL -> {
                    logger.error("FATAL ERROR: " + message);
//                    log.error("FATAL ERROR: " + message);
                }
                default -> {
                    logger.info(message);
//                    log.info(message);
                }
            }
        } catch (Exception e) {
            logError(e.getMessage());
        }
    }

    private String getStackTrace(Throwable throwable) {
        try (StringWriter stringWriter = new StringWriter(); PrintWriter printWriter = new PrintWriter(
                stringWriter)) {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            logError(e.getMessage());
        }

        return "";
    }

    private String getLineInfo() {
        return "[" + Thread.currentThread().getStackTrace()[3].getMethodName() + ":"
                + Thread.currentThread().getStackTrace()[3].getLineNumber() + "]";
    }
}

