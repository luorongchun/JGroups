package org.jgroups.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.IllegalFormatException;

/**
 * Logger that delivers messages to a Log4J logger
 * 
 * @author Manik Surtani
 * @author Bela Ban
 * @since 2.8
 */
public class Log4JLogImpl implements Log {

    private static final String FQCN = Log4JLogImpl.class.getName();

    private final Logger logger;

    public Log4JLogImpl(String category) {
        logger = Logger.getLogger(category);
    }

    public Log4JLogImpl(Class<?> category) {
        logger = Logger.getLogger(category);
    }

    public boolean isFatalEnabled() {
        return logger.isEnabledFor(Level.FATAL);
    }

    public boolean isErrorEnabled() {
        return logger.isEnabledFor(Level.ERROR);
    }

    public boolean isWarnEnabled() {
        return logger.isEnabledFor(Level.WARN);
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public void debug(String msg) {
        logger.log(FQCN,Level.DEBUG,msg,null);
    }

    public void debug(String msg, Object... args) {
        if(isDebugEnabled())
            debug(format(msg, args));
    }

    public void debug(String msg, Throwable throwable) {
        logger.log(FQCN,Level.DEBUG,msg,throwable);
    }

    public void error(String msg) {
        logger.log(FQCN,Level.ERROR,msg,null);
    }

    public void error(String format, Object... args) {
        if(isErrorEnabled())
            error(format(format,args));
    }

    public void error(String msg, Throwable throwable) {
        logger.log(FQCN,Level.ERROR,msg,throwable);
    }

    public void fatal(String msg) {
        logger.log(FQCN,Level.FATAL,msg,null);
    }

    public void fatal(String msg, Object... args) {
        if(isFatalEnabled())
            fatal(format(msg, args));
    }

    public void fatal(String msg, Throwable throwable) {
        logger.log(FQCN,Level.FATAL,msg,throwable);
    }

    public void info(String msg) {
        logger.log(FQCN,Level.INFO,msg,null);
    }

    public void info(String msg, Object... args) {
        if(isInfoEnabled())
            info(format(msg, args));
    }

    public void trace(Object msg) {
        logger.log(FQCN,Level.TRACE,msg,null);
    }

    public void trace(String msg) {
        logger.log(FQCN,Level.TRACE,msg,null);
    }

    public void trace(String msg, Object... args) {
        if(isTraceEnabled())
            trace(format(msg, args));
    }

    public void trace(String msg, Throwable throwable) {
        logger.log(FQCN,Level.TRACE,msg,throwable);
    }

    public void warn(String msg) {
        logger.log(FQCN,Level.WARN,msg,null);
    }

    public void warn(String msg, Object... args) {
        if(isWarnEnabled())
            warn(format(msg, args));
    }

    public void warn(String msg, Throwable throwable) {
        logger.log(FQCN,Level.WARN,msg,throwable);
    }

    public String getLevel() {
        Level level = logger.getLevel();
        return level != null ? level.toString() : "off";
    }

    public void setLevel(String level) {
        Level new_level = strToLevel(level);
        if (new_level != null)
            logger.setLevel(new_level);
    }

    protected String format(String format, Object... args) {
        try {
            return String.format(format, args);
        }
        catch(IllegalFormatException ex) {
            error("Illegal format string \"" + format + "\", args=" + Arrays.toString(args));
        }
        catch(Throwable t) {
            error("Failure formatting string: format string=" + format + ", args=" + Arrays.toString(args));
        }
        return format;
    }

    protected static Level strToLevel(String level) {
        if (level == null)
            return null;
        level = level.toLowerCase().trim();
        if (level.equals("fatal"))
            return Level.FATAL;
        if (level.equals("error"))
            return Level.ERROR;
        if (level.equals("warn"))
            return Level.WARN;
        if (level.equals("warning"))
            return Level.WARN;
        if (level.equals("info"))
            return Level.INFO;
        if (level.equals("debug"))
            return Level.DEBUG;
        if (level.equals("trace"))
            return Level.TRACE;
        return null;
    }
}
