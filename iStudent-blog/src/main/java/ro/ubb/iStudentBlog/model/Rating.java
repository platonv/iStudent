package ro.ubb.iStudentBlog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ciprian.mosincat on 12/18/2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ratings")
public class Rating {

    @Id
    private String uuid;
    private double rate;
}
