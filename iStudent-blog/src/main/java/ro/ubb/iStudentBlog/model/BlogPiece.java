package ro.ubb.iStudentBlog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "blogpiece")
public class BlogPiece {

    @Id
    private String uuid;
    private String content;
    //here it should be a User class but that will change once auth is finished
    private String user;
    private List<Rating> ratings;

}
