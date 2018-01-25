package ro.ubb.iStudentBlog.logging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.event.Level;

/**
 * @author ciprian.mosincat on 12/30/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {

    private Level level;
    private String message;

}
