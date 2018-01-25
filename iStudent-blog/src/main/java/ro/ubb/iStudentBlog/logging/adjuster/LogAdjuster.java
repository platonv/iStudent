package ro.ubb.iStudentBlog.logging.adjuster;

import ro.ubb.iStudentBlog.logging.LogEvent;

/**
 * @author ciprian.mosincat on 12/30/2017.
 */
public interface LogAdjuster {

    boolean log(LogEvent logEvent);

}
