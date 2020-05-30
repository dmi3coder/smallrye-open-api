package io.smallrye.openapi.api.util;

import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.Cause;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;

@MessageLogger(projectCode = "SROAP", length = 5)
interface UtilLogging extends BasicLogger {
    UtilLogging log = Logger.getMessageLogger(UtilLogging.class, UtilLogging.class.getPackage().getName());

    @LogMessage(level = Logger.Level.ERROR)
    @Message(id = 1000, value = "Failed to introspect BeanInfo for: %s")
    void failedToIntrospectBeanInfo(Class<?> clazz, @Cause Throwable cause);
}
