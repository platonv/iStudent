package ro.ubb.iStudentBlog.logging.adjuster;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ro.ubb.iStudentBlog.logging.LogEvent;

/**
 * @author ciprian.mosincat on 12/30/2017.
 */
@Slf4j
@Component
public class LogWarnAdjuster implements LogAdjuster {

    @Override
    public boolean log(final LogEvent logEvent) {
        if (Level.WARN.equals(logEvent.getLevel())) {
            log.warn(logEvent.getMessage());
            return true;
        }
        return false;
    }

}