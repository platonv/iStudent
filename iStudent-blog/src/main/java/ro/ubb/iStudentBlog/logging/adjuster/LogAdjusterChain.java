package ro.ubb.iStudentBlog.logging.adjuster;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.ubb.iStudentBlog.logging.LogEvent;

import java.util.Arrays;
import java.util.List;

/**
 * @author ciprian.mosincat on 12/30/2017.
 */
@Slf4j
@Component
public class LogAdjusterChain {

    private final List<LogAdjuster> logAdjusters;

    public LogAdjusterChain(final LogTraceAdjuster logTraceAdjuster,
                            final LogDebugAdjuster logDebugAdjuster,
                            final LogInfoAdjuster logInfoAdjuster,
                            final LogWarnAdjuster logWarnAdjuster,
                            final LogErrorAdjuster logErrorAdjuster) {
        this.logAdjusters = Arrays.asList(logTraceAdjuster, logDebugAdjuster,
                logInfoAdjuster, logWarnAdjuster, logErrorAdjuster);
    }

    public void doChainAdjust(final LogEvent logEvent) {
        boolean continueAdjust = false;
        for (final LogAdjuster logAdjuster : logAdjusters) {
            continueAdjust = logAdjuster.log(logEvent);
            if (continueAdjust) {
                break;
            }
        }
        if (!continueAdjust) {
            log.warn("Unsupported log level: " + logEvent.getLevel().toString());
        }
    }

}
