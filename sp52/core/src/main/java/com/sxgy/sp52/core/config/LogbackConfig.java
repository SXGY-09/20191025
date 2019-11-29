package com.sxgy.sp52.core.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

/**
 * @author SXGY_09
 * @description 自定义日志颜色
 * @date 2019-11-29 21:06
 */
public class LogbackConfig extends ForegroundCompositeConverterBase<ILoggingEvent> {
    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.INFO_INT:
                return ANSIConstants.DEFAULT_FG;
            case Level.DEBUG_INT:
                return ANSIConstants.GREEN_FG;
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }
}
